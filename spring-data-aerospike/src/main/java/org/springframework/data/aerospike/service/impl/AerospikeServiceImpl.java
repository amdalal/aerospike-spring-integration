package org.springframework.data.aerospike.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.data.aerospike.core.GenericVOWrapper;
import org.springframework.data.aerospike.core.LoadAerospikeConfigRequest;
import org.springframework.data.aerospike.exception.DuplicateBinNameException;
import org.springframework.data.aerospike.exception.GetFailedException;
import org.springframework.data.aerospike.exception.KeyNotDefinedException;
import org.springframework.data.aerospike.exception.LoadFailedException;
import org.springframework.data.aerospike.exception.PutFailedException;
import org.springframework.data.aerospike.exception.RemoveFailedException;
import org.springframework.data.aerospike.helper.ClassReader;
import org.springframework.data.aerospike.helper.SetterGetterHelper;
import org.springframework.data.aerospike.helper.StringUtils;
import org.springframework.data.aerospike.service.IAerospikeService;
import org.springframework.data.aerospike.service.VOMarker;
import org.springframework.stereotype.Service;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.AerospikeException;
import com.aerospike.client.Bin;
import com.aerospike.client.Host;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.Value;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.WritePolicy;

@Service("aerospikeService")
public class AerospikeServiceImpl implements IAerospikeService {

    private static final WritePolicy                         policy             = new WritePolicy();

    private Map<Class<? extends VOMarker>, GenericVOWrapper> markerToWrapperMap = new HashMap<Class<? extends VOMarker>, GenericVOWrapper>();

    private static AerospikeClient                           client;

    private static ClientPolicy                              clientPolicy;

    @Override
    public void put(VOMarker vo) throws PutFailedException {
        GenericVOWrapper voWrapper = markerToWrapperMap.get(vo.getClass());
        Bin[] bins = new Bin[voWrapper.getBinNameToGetterMap().size()];
        int index = 0;
        for (Entry<String, Method> e : voWrapper.getBinNameToGetterMap().entrySet()) {
            try {
                bins[index] = new Bin(e.getKey(), Value.get(e.getValue().invoke(vo, new Object[] {})));
            } catch (Exception e1) {
                throw new PutFailedException("Failed to invoke getter method on VO", e1);
            }
            index++;
        }
        Key key = null;
        try {
            key = new Key(voWrapper.getNamespace(), voWrapper.getSet(), Value.get(voWrapper.getBinNameToGetterMap().get(voWrapper.getKeyBinName()).invoke(vo, new Object[] {})));
        } catch (Exception e1) {
            throw new PutFailedException("Failed to invoke getter method on VO while fetching key", e1);
        }
        try {
            client.put(policy, key, bins);
        } catch (AerospikeException e1) {
            throw new PutFailedException("Failed while putting VO in Aerospike", e1);
        } catch (Throwable t) {
            throw new PutFailedException("Throwable encountered while putting VO in Aerospike", t);
        }
    }

    @Override
    public VOMarker get(String key, Class<? extends VOMarker> clazz) throws GetFailedException {
        return getVOFromRecord(key, clazz);
    }

    @Override
    public VOMarker get(Integer key, Class<? extends VOMarker> clazz) throws GetFailedException {
        return getVOFromRecord(key, clazz);
    }

    @Override
    public VOMarker get(Long key, Class<? extends VOMarker> clazz) throws GetFailedException {
        return getVOFromRecord(key, clazz);
    }

    @Override
    public VOMarker get(Float key, Class<? extends VOMarker> clazz) throws GetFailedException {
        return getVOFromRecord(key, clazz);
    }

    @Override
    public VOMarker get(Byte key, Class<? extends VOMarker> clazz) throws GetFailedException {
        return getVOFromRecord(key, clazz);
    }

    @Override
    public VOMarker get(Double key, Class<? extends VOMarker> clazz) throws GetFailedException {
        return getVOFromRecord(key, clazz);
    }

    @Override
    public VOMarker get(Character key, Class<? extends VOMarker> clazz) throws GetFailedException {
        return getVOFromRecord(key, clazz);
    }

    @Override
    public VOMarker get(Boolean key, Class<? extends VOMarker> clazz) throws GetFailedException {
        return getVOFromRecord(key, clazz);
    }

    private VOMarker getVOFromRecord(Object key, Class<? extends VOMarker> clazz) throws GetFailedException {
        GenericVOWrapper voWrapper = markerToWrapperMap.get(clazz);
        Record record = null;
        try {
            record = client.get(policy, new Key(voWrapper.getNamespace(), voWrapper.getSet(), key));
        } catch (AerospikeException e2) {
            throw new GetFailedException("AerospikeException", e2);
        } catch (Throwable t) {
            throw new GetFailedException("Throwable encountered", t);
        }
        VOMarker vo = null;
        if (record != null) {
            try {
                vo = (VOMarker) clazz.newInstance();
            } catch (InstantiationException e1) {
                throw new GetFailedException("Failed to instantiate VO", e1);
            } catch (IllegalAccessException e1) {
                throw new GetFailedException("Failed to instantiate VO", e1);
            }
            for (Entry<String, Method> e : voWrapper.getBinNameToSetterMap().entrySet()) {
                try {
                    if (e.getValue().getParameterTypes()[0].equals(Long.class)) {
                        e.getValue().invoke(vo, new Long((Integer) record.getValue(e.getKey())));
                    } else {
                        e.getValue().invoke(vo, record.getValue(e.getKey()));
                    }
                } catch (Exception e1) {
                    throw new GetFailedException("Failed to invoke setter on VO", e1);
                }
            }
        }
        return vo;
    }

    @Override
    public boolean remove(String key, Class<? extends VOMarker> clazz) throws RemoveFailedException {
        return removeVOForRecord(key, clazz);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void loadAerospikeConfig(LoadAerospikeConfigRequest request) throws LoadFailedException {
        try {
            initializeClient(request);
            for (Class clazz : ClassReader.getClasses(request.getPackageToScan())) {
                org.springframework.data.aerospike.annotation.Set setAnnotation = (org.springframework.data.aerospike.annotation.Set) clazz.getAnnotation(org.springframework.data.aerospike.annotation.Set.class);
                if (setAnnotation != null) {
                    GenericVOWrapper voWrapper = new GenericVOWrapper();
                    voWrapper.setNamespace(setAnnotation.namespace());
                    voWrapper.setSet(setAnnotation.name());
                    for (Field f : clazz.getDeclaredFields()) {
                        org.springframework.data.aerospike.annotation.Bin binAnnotation = (org.springframework.data.aerospike.annotation.Bin) f.getAnnotation(org.springframework.data.aerospike.annotation.Bin.class);
                        if (binAnnotation != null) {
                            if (voWrapper.getBinNameToGetterMap().put(binAnnotation.name(), clazz.getMethod(SetterGetterHelper.getGetterName(f), new Class[] {})) != null) {
                                throw new DuplicateBinNameException("Duplicate bin Name: " + binAnnotation.name());
                            }
                            if (voWrapper.getBinNameToSetterMap().put(binAnnotation.name(), clazz.getMethod(SetterGetterHelper.getSetterName(f), new Class[] { f.getType() })) != null) {
                                throw new DuplicateBinNameException("Duplicate bin Name: " + binAnnotation.name());
                            }
                        }
                        org.springframework.data.aerospike.annotation.Key keyAnnotation = (org.springframework.data.aerospike.annotation.Key) f.getAnnotation(org.springframework.data.aerospike.annotation.Key.class);
                        if (keyAnnotation != null) {
                            voWrapper.setKeyBinName(binAnnotation.name());
                        }
                    }
                    if (StringUtils.isEmpty(voWrapper.getKeyBinName())) {
                        throw new KeyNotDefinedException("No Key defined for Set: " + setAnnotation.name() + " in namespace: " + setAnnotation.namespace());
                    }
                    markerToWrapperMap.put(clazz, voWrapper);
                }
            }
        } catch (Exception e) {
            throw new LoadFailedException(e);
        }
    }

    private void initializeClient(LoadAerospikeConfigRequest request) throws Exception {
        List<String> endPointSplit = StringUtils.split(request.getEndPoint(), ":");
        clientPolicy = new ClientPolicy();
        clientPolicy.failIfNotConnected = request.isFailIfNotConnected();
        clientPolicy.maxSocketIdle = request.getMaxSocketIdle();
        clientPolicy.maxThreads = request.getMaxThreads();
        clientPolicy.timeout = request.getTimeout();
        client = new AerospikeClient(clientPolicy, new Host(endPointSplit.get(0).trim(), Integer.parseInt(endPointSplit.get(1).trim())));
    }

    @Override
    public boolean remove(Long key, Class<? extends VOMarker> clazz) throws RemoveFailedException {
        return removeVOForRecord(key, clazz);
    }

    @Override
    public boolean remove(Integer key, Class<? extends VOMarker> clazz) throws RemoveFailedException {
        return removeVOForRecord(key, clazz);
    }

    @Override
    public boolean remove(Float key, Class<? extends VOMarker> clazz) throws RemoveFailedException {
        return removeVOForRecord(key, clazz);
    }

    @Override
    public boolean remove(Byte key, Class<? extends VOMarker> clazz) throws RemoveFailedException {
        return removeVOForRecord(key, clazz);
    }

    @Override
    public boolean remove(Double key, Class<? extends VOMarker> clazz) throws RemoveFailedException {
        return removeVOForRecord(key, clazz);
    }

    @Override
    public boolean remove(Boolean key, Class<? extends VOMarker> clazz) throws RemoveFailedException {
        return removeVOForRecord(key, clazz);
    }

    @Override
    public boolean remove(Character key, Class<? extends VOMarker> clazz) throws RemoveFailedException {
        return removeVOForRecord(key, clazz);
    }

    private boolean removeVOForRecord(Object key, Class<? extends VOMarker> clazz) throws RemoveFailedException {
        GenericVOWrapper voWrapper = markerToWrapperMap.get(clazz);
        try {
            return client.delete(policy, new Key(voWrapper.getNamespace(), voWrapper.getSet(), Value.get(key)));
        } catch (AerospikeException aex) {
            throw new RemoveFailedException("Failed to remove key: " + key, aex);
        } catch (Throwable t) {
            throw new RemoveFailedException("Throwable encountered while removing key: " + key, t);
        }
    }
}
