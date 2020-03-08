package com.example.demo.datasource;

/**
 * description: 动态数据源
 * create: 2019/1/29 15:25
 *
 * @author NieMingXin
 */

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}
