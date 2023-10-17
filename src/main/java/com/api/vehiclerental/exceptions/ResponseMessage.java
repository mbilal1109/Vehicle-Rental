package com.api.vehiclerental.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseMessage {

    private String message;
    private HttpStatus status;
    private boolean success;
    private Throwable cause;
}
