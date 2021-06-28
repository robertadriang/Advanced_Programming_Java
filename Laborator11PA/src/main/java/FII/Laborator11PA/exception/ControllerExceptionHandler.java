package FII.Laborator11PA.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import FII.Laborator11PA.exception.ErrorMessage;
import FII.Laborator11PA.exception.ResourceNotFoundException;

import java.sql.Date;


@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                404,
                new Date(System.currentTimeMillis()),
                ex.getMessage(),
                "Testing this request");

        return message;
    }
}