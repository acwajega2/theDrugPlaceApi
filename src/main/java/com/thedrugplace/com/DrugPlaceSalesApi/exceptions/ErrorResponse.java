package com.thedrugplace.com.DrugPlaceSalesApi.exceptions;

import lombok.Data;

/**
 * A class representing an error response with an error code, error message, and optional details.
 */
@Data
public class ErrorResponse {
    /**
     * The error code associated with the error.
     */
    private String errorCode;

    /**
     * The error message describing the error.
     */
    private String errorMessage;

    /**
     * Additional details about the error, if available.
     */
    private String details;

    /**
     * Constructs a new ErrorResponse with the specified error code and error message.
     *
     * @param errorCode    The error code.
     * @param errorMessage The error message.
     */
    public ErrorResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Constructs a new ErrorResponse with the specified error code, error message, and details.
     *
     * @param errorCode    The error code.
     * @param errorMessage The error message.
     * @param details      Additional details about the error.
     */
    public ErrorResponse(String errorCode, String errorMessage, String details) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.details = details;
    }
}
