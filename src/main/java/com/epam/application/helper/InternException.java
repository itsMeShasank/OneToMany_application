package com.epam.application.helper;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class InternException extends RuntimeException {
    private HttpStatus status;
    public InternException(String msg,HttpStatus status) {
        super(msg);
        this.status = status;

    }
}
