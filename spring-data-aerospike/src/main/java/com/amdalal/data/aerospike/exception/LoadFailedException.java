package com.amdalal.data.aerospike.exception;

public class LoadFailedException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 4474231508925302781L;

    public LoadFailedException() {
        super();
    }

    public LoadFailedException(Throwable cause) {
        super(cause);
    }

    public LoadFailedException(String message) {
        super(message);
    }

    public LoadFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
