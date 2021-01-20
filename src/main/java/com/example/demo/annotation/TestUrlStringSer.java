package com.example.demo.annotation;

import com.example.demo.util.TestSerializeUtil;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description:TestUrlStringSer
 * create: 2020/12/25 21:44
 *
 * @author MingXin.Nie
 * @version 1.0
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize(using = TestSerializeUtil.class)
@JacksonAnnotationsInside
public @interface TestUrlStringSer {
    String w() default "";

    String h() default "";

    String q() default "";

    String a() default "";
}
