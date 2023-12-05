package com.dg.ums.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class DGErrorMessage {

    private LocalDateTime timestamp;
    private String message;
}
