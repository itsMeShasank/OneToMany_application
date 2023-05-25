package com.epam.application.helper;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BatchException extends RuntimeException {
    private HttpStatus status;
    public BatchException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }
}
