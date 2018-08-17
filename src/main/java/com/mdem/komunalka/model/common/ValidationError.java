package com.mdem.komunalka.model.common;

import java.util.List;
import java.util.Map;

public class ValidationError {
    Map<String, String> validationErrors;

    public ValidationError(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
