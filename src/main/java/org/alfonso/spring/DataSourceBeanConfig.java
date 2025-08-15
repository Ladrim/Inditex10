package org.alfonso.spring;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceBeanConfig
{
    public DataSource dataSource(DataBaseBeanConfig config)
    {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();

        return dataSourceBuilder
            .url(config.getUrl())
            .driverClassName(config.getDriverClassName())
            .username(config.getUsername())
            .password(config.getPassword())
            .build();

    }
}
