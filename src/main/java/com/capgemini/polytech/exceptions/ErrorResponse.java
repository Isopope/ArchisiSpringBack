package com.capgemini.polytech.exceptions;

import lombok.Getter;

import java.util.Map;

public class ErrorResponse {

    // Getters et Setters
    @Getter
    private String errorCode;  // Code d'erreur
    private String errorMessage;  // Message d'erreur
    private Map<String, String> validationErrors;

    public ErrorResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }

}

