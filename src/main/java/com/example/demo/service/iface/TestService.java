package com.example.demo.service.iface;

import com.example.demo.common.Response;
import com.example.demo.model.UserInfo;

public interface TestService {
    Response<UserInfo> findOne();

    Response<UserInfo> find();
}

