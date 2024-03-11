package dev.andrearaujo.error;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ValidationErrorResponseTest {

    @Test
    public void testValidationErrorResponse() {
        String message = "exampleMessage";
        String field = "exampleField";
        String errorMessage = "exampleErrorMessage";

        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
        validationErrorResponse.setMessage(message);
        validationErrorResponse.addError(field, errorMessage);

        Assertions.assertEquals(message, validationErrorResponse.getMessage());

        List<ValidationError> errors = validationErrorResponse.getErrors();
        Assertions.assertNotNull(errors);
        Assertions.assertEquals(1, errors.size());

        ValidationError error = errors.get(0);
        Assertions.assertEquals(field, error.getField());
        Assertions.assertEquals(errorMessage, error.getMessage());
    }
}