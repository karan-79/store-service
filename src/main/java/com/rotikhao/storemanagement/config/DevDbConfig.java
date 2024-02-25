package com.rotikhao.storemanagement.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("dev")
public class DevDbConfig {

    @Bean
    @ConditionalOnProperty(name = "db", havingValue = "h2")
    public DataSource h2DataSource(){
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:testdb;MODE=PostgreSQL")
                .driverClassName("org.h2.Driver")
                .username("sa")
                .password("password")
                .build();
    }
}
