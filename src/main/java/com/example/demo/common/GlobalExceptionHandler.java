package com.example.demo.common;

import com.example.demo.annotation.ResponseCode;
import com.example.demo.constant.Constants;
import com.example.demo.constant.TestExceptionCode;
import com.example.demo.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * description: 全局异常处理
 * create: 2019/2/13 14:12
 *
 * @author NieMingXin
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response<String> handle(Exception e) {
        log.error("GlobalException", e);
        return Response.buildFail(ResultCode.ERROR.getCode(), e.getMessage());
    }

    /**
     * 处理全局异常handler, ApiException为业务异常
     */
    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public Response<String> handle(ApiException e) {
        log.error("ApiException", e);
        return Response.buildFail(e.getClass().isAnnotationPresent(ResponseCode.class) ? e.getClass().getAnnotation(ResponseCode.class).value() : TestExceptionCode.TEST_CODE, e.getMessage());
    }

    /**
     * create: 2020/8/23 17:48
     * description: @Validated check异常
     *
     * @param e:e
     * @return com.example.demo.common.Response<java.lang.String>
     * @author niemingxin
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response<String> bindExceptionHandle(MethodArgumentNotValidException e) {
        log.error("parameter error", e);
        //如果check字段,有多个字段未满足要求,则拼接在一起返回
        String message = e.getBindingResult().getFieldErrors().stream().map(x -> x.getField() + Constants.COLON + x.getDefaultMessage()).collect(Collectors.joining(Constants.COMMA));
        return Response.buildFail("1", message);
    }

    /**
     * create: 2020/8/23 17:48
     * description: @Validated check param和header异常
     *
     * @param e:e
     * @return cn.com.pg.rtir.common.result.ResultWrapper<java.lang.String>
     * @author niemingxin
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Response<String> bindExceptionHandle(ConstraintViolationException e) {
        log.error("parameter error", e);
        return Response.buildFail("1", e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(Constants.COMMA)));
    }
}
