package com.vominh.example.app.config.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class RouteDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<DataSourceType> currentDataSource = new ThreadLocal<>();

    private final Logger log = LoggerFactory.getLogger(SpringDataConfig.class);

    public RouteDataSource(DataSource writeDataSource, DataSource readDataSource) {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceType.WRITE, writeDataSource);
        dataSourceMap.put(DataSourceType.READ, readDataSource);
        setTargetDataSources(dataSourceMap);
        setDefaultTargetDataSource(writeDataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        log.info("Current dataSourceType : {}", currentDataSource.get());
        return currentDataSource.get();
    }

    static void setCurrentDataSource(boolean isReadonly) {
        currentDataSource.set(isReadonly ? DataSourceType.READ : DataSourceType.WRITE);
    }
}

enum DataSourceType {
    WRITE,
    READ
}


