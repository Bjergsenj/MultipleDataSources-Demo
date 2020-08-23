package com.example.demo.common;

import com.example.demo.enums.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * description:统一封装返回结果
 * create: 2020/3/12 18:50
 *
 * @author NieMingXin
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class Response<T> implements Serializable {

    private Boolean success;

    private String code;

    private String message;

    private T data;

    public static <T> Response<T> buildSuccess(T data) {
        return new Response<>(true, data, ResultCode.FORMAL);
    }

    public static <T> Response<T> buildFail(ResultCode returnCode) {
        return new Response<>(false, null, returnCode.getCode(), returnCode.getMessage());
    }

    public static <T> Response<T> buildFail(String code, String message) {
        return new Response<>(false, null, code, message);
    }

    private Response(Boolean success, T data, String code, String message) {
        this.success = success;
        this.data = data;
        this.code = code;
        this.message = message;
    }

    private Response(Boolean success, T data, ResultCode resultCode) {
        this.success = success;
        this.data = data;
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }
}
