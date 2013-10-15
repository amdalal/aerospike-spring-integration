package org.springframework.data.aerospike.service;

import org.springframework.data.aerospike.core.LoadAerospikeConfigRequest;
import org.springframework.data.aerospike.exception.GetFailedException;
import org.springframework.data.aerospike.exception.LoadFailedException;
import org.springframework.data.aerospike.exception.PutFailedException;
import org.springframework.data.aerospike.exception.RemoveFailedException;

public interface IAerospikeService {

    void put(VOMarker vo) throws PutFailedException;

    VOMarker get(String key, Class<? extends VOMarker> clazz) throws GetFailedException;

    VOMarker get(Long key, Class<? extends VOMarker> clazz) throws GetFailedException;

    VOMarker get(Integer key, Class<? extends VOMarker> clazz) throws GetFailedException;

    VOMarker get(Float key, Class<? extends VOMarker> clazz) throws GetFailedException;

    VOMarker get(Byte key, Class<? extends VOMarker> clazz) throws GetFailedException;

    VOMarker get(Double key, Class<? extends VOMarker> clazz) throws GetFailedException;

    VOMarker get(Boolean key, Class<? extends VOMarker> clazz) throws GetFailedException;

    VOMarker get(Character key, Class<? extends VOMarker> clazz) throws GetFailedException;


    boolean remove(String key, Class<? extends VOMarker> clazz) throws RemoveFailedException;

    boolean remove(Long key, Class<? extends VOMarker> clazz) throws RemoveFailedException;

    boolean remove(Integer key, Class<? extends VOMarker> clazz) throws RemoveFailedException;

    boolean remove(Float key, Class<? extends VOMarker> clazz) throws RemoveFailedException;

    boolean remove(Byte key, Class<? extends VOMarker> clazz) throws RemoveFailedException;

    boolean remove(Double key, Class<? extends VOMarker> clazz) throws RemoveFailedException;

    boolean remove(Boolean key, Class<? extends VOMarker> clazz) throws RemoveFailedException;

    boolean remove(Character key, Class<? extends VOMarker> clazz) throws RemoveFailedException;
    
    /**
     * Loads the context. This should be called at startup.
     * 
     * @throws LoadFailedException
     */
    void loadAerospikeConfig(LoadAerospikeConfigRequest request) throws LoadFailedException;

}
