package com.thedrugplace.com.DrugPlaceSalesApi.exceptions;

/**
 * A custom runtime exception class that can be used to represent custom exceptions in the application.
 */
public class CustomException extends RuntimeException {
    /**
     * Constructs a new CustomException with the specified detail message.
     *
     * @param message the detail message
     */
    public CustomException(String message) {
        super(message);
    }
}
