package org.springframework.data.aerospike.core;

public class LoadAerospikeConfigRequest {

    /**
     * End point of any one node of the Aerospike server. Format: <b>IP:PORT.</b>
     */
    private String  endPoint;
    /**
     * Package which contains all the object definitions which are to be saved in Aerospike
     */
    private String  packageToScan;
    
    private boolean failIfNotConnected;
    
    private int     maxSocketIdle;
    
    /**
     * Maximum number of database client threads that you will have at any point in time. Default = 300.
     */
    private int     maxThreads;
    
    private int     timeout;

    public LoadAerospikeConfigRequest() {

    }

    public LoadAerospikeConfigRequest(String endPoint, String packageToScan, boolean failIfNotConnected, int maxSocketIdle, int maxThreads, int timeout) {
        super();
        this.endPoint = endPoint;
        this.packageToScan = packageToScan;
        this.failIfNotConnected = failIfNotConnected;
        this.maxSocketIdle = maxSocketIdle;
        this.maxThreads = maxThreads;
        this.timeout = timeout;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getPackageToScan() {
        return packageToScan;
    }

    public void setPackageToScan(String packageToScan) {
        this.packageToScan = packageToScan;
    }

    public boolean isFailIfNotConnected() {
        return failIfNotConnected;
    }

    public void setFailIfNotConnected(boolean failIfNotConnected) {
        this.failIfNotConnected = failIfNotConnected;
    }

    public int getMaxSocketIdle() {
        return maxSocketIdle;
    }

    public void setMaxSocketIdle(int maxSocketIdle) {
        this.maxSocketIdle = maxSocketIdle;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

}
