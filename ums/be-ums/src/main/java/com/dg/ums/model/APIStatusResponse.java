package com.dg.ums.model;

import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class APIStatusResponse {

    private HttpStatus status;

    private String message;
}
