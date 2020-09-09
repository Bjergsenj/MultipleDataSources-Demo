package com.example.demo.controller;

import com.example.demo.annotation.Limit;
import com.example.demo.enums.LimitType;
import com.example.demo.model.UserInfo;
import com.example.demo.service.impl.DemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * description:DemoController
 * create: 2020/9/9 10:00
 *
 * @author NieMingXin
 * @version 1.0
 */
@RestController
public class DemoController {
    @Autowired
    private DemoServiceImpl demoService;

    @PostMapping(value = "/test")
    @Limit(ttl = 5, timeUnit = TimeUnit.MINUTES, count = 10, key = "#userInfo.nick", prefix = "limit", limitType = LimitType.EL)
    public String test(@RequestBody UserInfo userInfo) {

        return "ok";
    }

    @GetMapping(value = "/test1")
    @Limit(ttl = 1, timeUnit = TimeUnit.MINUTES, count = 10, key = "#name", prefix = "limit")
    public String test1(@RequestParam("name") String name, @RequestParam("email") String email) {

        return demoService.test(name, email);
    }
}
