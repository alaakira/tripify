package com.tripify.demo.message_handler;

import com.tripify.demo.exceptions.IncorrectPasswordException;
import com.tripify.demo.exceptions.UserExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApplicationError> handleUsernameNotFoundException(UsernameNotFoundException exception, WebRequest webRequest) {
        exception.printStackTrace();
        ApplicationError error = new ApplicationError();
        error.setCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ApplicationError> handleIncorrectPasswordException(IncorrectPasswordException exception, WebRequest webRequest) {
        exception.printStackTrace();
        ApplicationError error = new ApplicationError();
        error.setCode(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
        error.setMessage(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ApplicationError> handleUserFoundException(UserExistsException exception, WebRequest webRequest) {
        exception.printStackTrace();
        ApplicationError error = new ApplicationError();
        error.setCode(HttpStatus.FOUND.value());
        error.setMessage(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApplicationError> handleRuntimeException(RuntimeException exception, WebRequest webRequest) {
        exception.printStackTrace();
        ApplicationError error = new ApplicationError();
        error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
