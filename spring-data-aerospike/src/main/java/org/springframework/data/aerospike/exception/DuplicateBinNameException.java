package org.springframework.data.aerospike.exception;

import org.springframework.data.aerospike.annotation.Bin;

/**
 * Indicates same {@link Bin} name has been used multiple times in a object to be saved in Aerospike.
 * 
 */
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
