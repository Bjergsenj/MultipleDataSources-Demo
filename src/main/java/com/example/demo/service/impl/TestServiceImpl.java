package com.example.demo.service.impl;

import com.example.demo.annotation.DataSource;
import com.example.demo.common.DataSourceType;
import com.example.demo.common.Response;
import com.example.demo.dao.mapper.UserInfoMapper;
import com.example.demo.model.UserInfo;
import com.example.demo.service.iface.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Response<UserInfo> findOne() {
        UserInfo userInfo = userInfoMapper.selectById(1L);
        Response<UserInfo> response = new Response<>();
        response.setResult(userInfo);
        response.setMessage("yes");
        return response;
    }

    @Override
    @DataSource(DataSourceType.MASTER)
    public Response<UserInfo> find() {
        UserInfo userInfo = userInfoMapper.selectById(1L);
        Response<UserInfo> response = new Response<>();
        response.setResult(userInfo);
        response.setMessage("yes");
        return response;
    }
}
