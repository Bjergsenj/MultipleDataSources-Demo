package com.example.demo.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * description:DemoServiceImpl
 * create: 2020/9/9 10:39
 *
 * @author NieMingXin
 * @version 1.0
 */
@Service
public class DemoServiceImpl {

    @Cacheable(value = "test", key = "#name+ '_' + #email")
    public String test(String name, String email) {
        return name + email;
    }
}
