package com.example.demo.common;

import com.example.demo.annotation.ResponseCode;

@ResponseCode
public class ApiException extends RuntimeException {
    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
    }


    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }
}
