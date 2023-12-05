package com.dg.ums.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Getter
@AllArgsConstructor
public class DGValidationError {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public DGValidationError(HttpStatus status, String message, String error){
        this.status = status;
        this.message = message;
        this.errors = Arrays.asList(error);
    }

}
