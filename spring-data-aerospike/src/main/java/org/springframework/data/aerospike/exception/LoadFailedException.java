package org.springframework.data.aerospike.exception;

public class LoadFailedException extends Exception {


    /**
     * 
     */
    private static final long serialVersionUID = -7400433566190552317L;

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
