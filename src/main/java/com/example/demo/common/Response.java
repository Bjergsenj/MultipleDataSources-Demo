package com.example.demo.common;

import com.example.demo.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> implements Serializable {

    private Boolean success;

    private String code;

    private String message;

    private T data;

    public static <T> Response<T> buildResponse(T data) {
        return new Response<T>(true, ResultCode.FORMAL.value(), null, data);
    }

    public void setResult(T data) {
        this.success = true;
        this.code = ResultCode.FORMAL.value();
        this.data = data;

    }

}
