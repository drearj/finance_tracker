package dev.andrearaujo.error;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidationErrorTest {

    @Test
    public void testValidationError() {
        String field = "exampleField";
        String message = "exampleMessage";

        ValidationError validationError = new ValidationError();
        validationError.setField(field);
        validationError.setMessage(message);

        Assertions.assertEquals(field, validationError.getField());
        Assertions.assertEquals(message, validationError.getMessage());
    }
}