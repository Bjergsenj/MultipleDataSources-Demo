
package com.example.demo.annotation;


import com.example.demo.enums.LimitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * description: redis限流注解
 * create: 2019/9/9 15:21
 *
 * @author NieMingXin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {
    /**
     * 资源 key (LimitType为EL时 支持Spring EL表达式)
     */
    String key() default "";

    /**
     * key prefix
     */
    String prefix() default "";

    /**
     * 时间单位  默认秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 过期时间
     */
    long ttl() default 1000L;

    /**
     * 限制访问次数
     */
    int count();

    /**
     * 限制类型 默认为自定义Key
     */
    LimitType limitType() default LimitType.CUSTOMER;

}
