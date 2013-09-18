package com.amdalal.data.aerospike.exception;

public class RemoveFailedException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -6838585820153716658L;

    public RemoveFailedException() {
        super();
    }

    public RemoveFailedException(Throwable cause) {
        super(cause);
    }

    public RemoveFailedException(String message) {
        super(message);
    }

    public RemoveFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
