package com.epam.application.helper;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExceptionResponse {

    private String timestamp;
    private String status;
    private String error;
    private String path;
}
