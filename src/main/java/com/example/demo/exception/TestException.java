package com.example.demo.exception;

import com.example.demo.annotation.ResponseCode;
import com.example.demo.common.ApiException;

@ResponseCode("E11111")
public class TestException extends ApiException {
    public TestException() {
        super();
    }

    public TestException(String message) {
        super(message);
    }


    public TestException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestException(Throwable cause) {
        super(cause);
    }
}
