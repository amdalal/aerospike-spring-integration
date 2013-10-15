package org.springframework.data.aerospike.exception;

public class PutFailedException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -4916813187168718316L;

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
