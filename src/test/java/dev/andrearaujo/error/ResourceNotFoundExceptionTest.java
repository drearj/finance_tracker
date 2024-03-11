package dev.andrearaujo.error;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResourceNotFoundExceptionTest {

    @Test
    public void testResourceNotFoundException() {
        String errorMessage = "Resource not found";
        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);

        Assertions.assertEquals(errorMessage, exception.getMessage());
    }
}