package com.mastery.java.task.advice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mastery.java.task.exception.EmployeeBaseServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class EmployeeControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(EmployeeControllerAdvice.class);

    @ExceptionHandler(EmployeeBaseServiceException.class)
    public ResponseEntity<Object> serviceExceptionHandler(EmployeeBaseServiceException exception) {
        Map<String, Object> body = new HashMap<>();
        logger.error(exception.getMessage());
        body.put("message", exception.getMessage());
        if (exception.getMessage().contains("Update")) {
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, Object> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        Map<String, Object> body = new HashMap<>();
        logger.error(exception.getMessage());
        exception.getBindingResult().getAllErrors().forEach(e -> {
            String fieldName = ((FieldError) e).getField();
            String message = e.getDefaultMessage();
            body.put(fieldName, message);
        });
        return body;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidFormatException.class})
    public Map<String, Object> methodArgumentNotValidHandler(InvalidFormatException exception) {
        Map<String, Object> body = new HashMap<>();
        logger.error(exception.getMessage());
        if (exception.getMessage().contains("LocalDate")) {
            body.put("dateOfBirth", "Such date format is invalid, right format is YYYY-MM-DD");
        }
        return body;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    public Map<String, Object> constraintViolationExceptionHandler(ConstraintViolationException exception) {
        Map<String, Object> body = new HashMap<>();
        logger.error(exception.getMessage());
        body.put("message", "Bad Request");
        return body;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public Map<String, Object> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException exception) {
        Map<String, Object> body = new HashMap<>();
        logger.error(exception.getMessage());
        body.put("message", "Bad Request");
        return body;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public Map<String, Object> anotherExceptionHandler(Exception exception) {
        logger.error(exception.getMessage());
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Internal Server Error");
        return body;
    }
}
