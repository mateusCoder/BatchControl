package com.mateus.exceptions.handler;

import com.mateus.exceptions.ObjectNotFound;
import com.mateus.exceptions.StandardError;
import com.mateus.exceptions.UnprocessableEntity;
import com.mateus.exceptions.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
class ResourceExceptionHandlerTest {

    @InjectMocks
    ResourceExceptionHandler resourceExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenValidationExceptionThenReturnObjectNotFound() {
        ResponseEntity<StandardError> response = resourceExceptionHandler
                .objectNotfound(
                        new ObjectNotFound("Object Not Found!"),
                        new MockHttpServletRequest()
                );

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("Object Not Found!", response.getBody().getMessage());
        assertEquals(404, response.getBody().getStatus());
    }

    @Test
    void whenValidationExceptionThenReturnBadRequest() {
        MethodParameter methodParameter = mock(MethodParameter.class);
        BindingResult bindingResult = mock(BindingResult.class);

        when(bindingResult.getFieldErrors()).thenReturn(Arrays.asList (new FieldError("object", "400", "bad request")));

        ResponseEntity<ValidationError> response = resourceExceptionHandler
                .validation(new MethodArgumentNotValidException(methodParameter, bindingResult));

        assertNotNull(response);
        assertNotNull(response.getBody());
        when(bindingResult.getFieldErrors()).thenReturn(Arrays.asList(new FieldError("object", "400", "bad request")));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    void whenValidationExceptionThenReturnUnprocessableEntity() {
        ResponseEntity<StandardError> response = resourceExceptionHandler
                .unprocessableEntity(
                        new UnprocessableEntity("Unprocessable Entity!"),
                        new MockHttpServletRequest()
                );

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("Unprocessable Entity!", response.getBody().getMessage());
        assertEquals(422, response.getBody().getStatus());

    }
}