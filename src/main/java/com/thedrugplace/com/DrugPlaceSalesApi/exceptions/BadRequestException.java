package com.thedrugplace.com.DrugPlaceSalesApi.exceptions;

/**
 * Exception class to indicate a bad request. This exception is typically thrown
 * when the client sends a request that is malformed or contains invalid data.
 */
public class BadRequestException extends RuntimeException {
    /**
     * Constructs a new BadRequestException with the specified detail message.
     *
     * @param message the detail message
     */
    public BadRequestException(String message) {
        super(message);
    }
}
