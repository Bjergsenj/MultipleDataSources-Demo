package com.example.demo.datasource;


import lombok.extern.slf4j.Slf4j;

/**
 * description: 数据源上下文
 * create: 2019/1/29 13:49
 *
 * @author NieMingXin
 */
@Slf4j
public class DataSourceContextHolder {
//    private static final String DEFAULT_DATASOURCE = "SlAVE_DATASOURCE";
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSource(String dbType) {
        log.info("切换到" + dbType + "数据源");
        contextHolder.set(dbType);
    }

    public static String getDataSource() {
        return contextHolder.get();
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }
}