package com.rotikhao.storemanagement.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("default")
public class DbConfig {

    @Bean
    @ConditionalOnMissingBean(DataSource.class)
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:3443/storedb")
                .driverClassName("org.postgresql.Driver")
                .username("karan")
                .password("karan")
                .build();
    }
}
