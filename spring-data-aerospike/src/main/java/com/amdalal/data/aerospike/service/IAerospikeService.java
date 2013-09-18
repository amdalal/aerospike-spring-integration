package com.amdalal.data.aerospike.service;

import com.amdalal.data.aerospike.exception.GetFailedException;
import com.amdalal.data.aerospike.exception.LoadFailedException;
import com.amdalal.data.aerospike.exception.PutFailedException;
import com.amdalal.data.aerospike.exception.RemoveFailedException;

public interface IAerospikeService {

    void put(VOMarker vo) throws PutFailedException;

    VOMarker get(String supc, Class<? extends VOMarker> clazz) throws GetFailedException;

    void loadAerospikeConfig(String endPoint, String packageToScan) throws LoadFailedException;
    
    boolean remove(String supc, Class<? extends VOMarker> clazz) throws RemoveFailedException;

}
