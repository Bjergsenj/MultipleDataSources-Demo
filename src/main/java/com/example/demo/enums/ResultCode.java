package com.example.demo.enums;

/**
 * description:返回结果code枚举
 * create: 2020/3/12 18:50
 *
 * @author NieMingXin
 * @version 1.0
 */
public enum ResultCode {
    /**
     * 业务正常code
     */
    FORMAL("0", "业务正常"),
    /**
     * 全局异常code
     */
    ERROR("E00000", "全局异常~~~~~");

    private String code;
    private String message;

    ResultCode(String value, String message) {
        this.code = value;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
