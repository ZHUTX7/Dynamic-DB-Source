package com.ztx.dynamicdbsource.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class DynamicDataSource extends AbstractRoutingDataSource {
    public static final String CLICKHOUSE="clickhouse";
    public static final String POSTGRE="postgre";


    @Override
    public Object determineCurrentLookupKey() {
        System.out.println("切换数据源到：" + ThreadLocalDataSource.getDataSource());
        return ThreadLocalDataSource.getDataSource();
    }
}
