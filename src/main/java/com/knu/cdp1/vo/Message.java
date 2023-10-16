package com.knu.cdp1.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class Message {
    private boolean isSuccess;
    private HttpStatus statusCode;
    private String message;
    private Object result;

    public void buildMessage(boolean isSuccess, HttpStatus statusCode, String message, Object result) {
        this.isSuccess = isSuccess;
        this.statusCode = statusCode;
        this.message = message;
        this.result = result;
    }
}
