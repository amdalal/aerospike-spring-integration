package com.amdalal.data.aerospike.exception;

public class PutFailedException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 4474231508925302781L;

    public PutFailedException() {
        super();
    }

    public PutFailedException(Throwable cause) {
        super(cause);
    }

    public PutFailedException(String message) {
        super(message);
    }

    public PutFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
