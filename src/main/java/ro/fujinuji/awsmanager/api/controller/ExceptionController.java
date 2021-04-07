package ro.fujinuji.awsmanager.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ro.fujinuji.awsmanager.model.exception.ConfigurationNotFoundException;
import ro.fujinuji.awsmanager.model.exception.MessageNotSentException;
import ro.fujinuji.awsmanager.model.exception.UserNotFoundException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ConfigurationNotFoundException.class)
    public ResponseEntity<?> handleConfigurationNotFound(ConfigurationNotFoundException configurationNotFoundException) {
        return new ResponseEntity<>(configurationNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MessageNotSentException.class)
    public ResponseEntity<?> handleMailNotSent(MessageNotSentException messageNotSentException) {
        return new ResponseEntity<>(messageNotSentException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
