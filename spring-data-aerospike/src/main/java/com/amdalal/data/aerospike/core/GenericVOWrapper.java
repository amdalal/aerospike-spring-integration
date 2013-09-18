package com.amdalal.data.aerospike.core;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class GenericVOWrapper implements Serializable {

    /**
     * 
     */
    private static final long   serialVersionUID   = -287320735903521324L;

    private String              namespace;

    private String              set;

    private String              keyBinName;

    private Map<String, Method> binNameToGetterMap = new HashMap<String, Method>();

    private Map<String, Method> binNameToSetterMap = new HashMap<String, Method>();

    public GenericVOWrapper() {
        super();
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public Map<String, Method> getBinNameToGetterMap() {
        return binNameToGetterMap;
    }

    public void setBinNameToGetterMap(Map<String, Method> binNameToGetterMap) {
        this.binNameToGetterMap = binNameToGetterMap;
    }

    public Map<String, Method> getBinNameToSetterMap() {
        return binNameToSetterMap;
    }

    public void setBinNameToSetterMap(Map<String, Method> binNameToSetterMap) {
        this.binNameToSetterMap = binNameToSetterMap;
    }

    public String getKeyBinName() {
        return keyBinName;
    }

    public void setKeyBinName(String key) {
        this.keyBinName = key;
    }

}
