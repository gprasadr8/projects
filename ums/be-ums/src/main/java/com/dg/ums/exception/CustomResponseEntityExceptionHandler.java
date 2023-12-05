package com.dg.ums.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<DGErrorMessage> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) throws Exception {
        DGErrorMessage errorMessage = new DGErrorMessage(LocalDateTime.now(),ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        DGErrorMessage errorMessage = new DGErrorMessage(LocalDateTime.now(),ex.getFieldError().getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request){

        List<String> errors = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessageTemplate).collect(Collectors.toList());
        DGValidationError validationError = new DGValidationError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DGErrorMessage> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
        DGErrorMessage errorMessage = new DGErrorMessage(LocalDateTime.now(),ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
