package com.example.demo.enums;

public enum ResultCode {

    FORMAL("0", "业务正常"),
    error("E00000","全局异常~~~~~");

    private String value;
    private String message;

    ResultCode(String value, String message) {
        this.value = value;
        this.message = message;
    }

    public String value() {
        return value;
    }

    public String getMessage() {
        return message;
    }

}
