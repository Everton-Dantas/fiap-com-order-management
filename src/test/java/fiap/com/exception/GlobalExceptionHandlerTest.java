package fiap.com.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void testHandleResponseStatusException() {
        ResponseStatusException exception = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Request");
        String response = globalExceptionHandler.handleResponseStatusException(exception);
        assertEquals("Invalid Request", response);
    }

    @Test
    void testHandleGenericException() {
        Exception exception = new Exception("Unexpected Error");
        String response = globalExceptionHandler.handleGenericException(exception);
        assertTrue(response.contains("Unexpected Error"));
    }
}