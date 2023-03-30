package com.vominh.example.scalableweb.config.data;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrimaryDataSource {

    @Bean
//    @Primary
    @ConfigurationProperties("spring.datasource.primary")
    public DataSourceProperties primaryDataSourceProperty() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.primary.hikari")
    public HikariDataSource writeDataSource() {
        return primaryDataSourceProperty().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

}
