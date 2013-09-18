package com.amdalal.data.aerospike.exception;

public class GetFailedException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 4474231508925302781L;

    public GetFailedException() {
        super();
    }

    public GetFailedException(Throwable cause) {
        super(cause);
    }

    public GetFailedException(String message) {
        super(message);
    }

    public GetFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
