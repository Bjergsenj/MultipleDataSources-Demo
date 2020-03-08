package com.example.demo.annotation;


import java.lang.annotation.*;

/**
 * description: 数据源注解
 * create: 2019/1/29 15:21
 *
 * @author NieMingXin
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseCode {

    String value() default "E00000";
}

