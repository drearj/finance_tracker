package dev.andrearaujo.error;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {

    private String message;
    private List<ValidationError> errors = new ArrayList<>();

    public void addError(String field, String message) {
        ValidationError error = new ValidationError();
        error.setField(field);
        error.setMessage(message);
        errors.add(error);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }
}