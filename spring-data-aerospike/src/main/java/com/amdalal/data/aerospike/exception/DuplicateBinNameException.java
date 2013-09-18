package com.amdalal.data.aerospike.exception;

public class DuplicateBinNameException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 4474231508925302781L;

    public DuplicateBinNameException() {
        super();
    }

    public DuplicateBinNameException(String message) {
        super(message);
    }

    public DuplicateBinNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
