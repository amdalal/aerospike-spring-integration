package org.springframework.data.aerospike.exception;

public class GetFailedException extends Exception {


    /**
     * 
     */
    private static final long serialVersionUID = -8561216170387012324L;

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
