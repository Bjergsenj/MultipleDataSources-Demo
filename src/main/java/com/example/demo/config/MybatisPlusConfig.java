package com.example.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * description:MybatisPlusConfig
 * create: 2020/9/7 17:24
 *
 * @author NieMingXin
 * @version 1.0
 */
@Configuration
@MapperScan("com.example.demo.dao.mapper")
public class MybatisPlusConfig {
}
