package com.example.demo.exception;

import com.example.demo.annotation.ResponseCode;
import com.example.demo.common.ApiException;

/**
 * @author NieMingXin
 * @version 1.0
 * @description oss 存储空间不存在
 * @create 2020/2/18 12:31
 */
@ResponseCode("E22221")
public class OSSNoSuchBucketException extends ApiException {

    public OSSNoSuchBucketException() {
        super();
    }

    public OSSNoSuchBucketException(String message) {
        super(message);
    }


    public OSSNoSuchBucketException(String message, Throwable cause) {
        super(message, cause);
    }

    public OSSNoSuchBucketException(Throwable cause) {
        super(cause);
    }

}
