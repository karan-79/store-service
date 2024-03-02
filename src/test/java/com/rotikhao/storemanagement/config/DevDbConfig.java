package com.rotikhao.storemanagement.config;

import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@Configuration
@Profile("dev")
public class DevDbConfig {

    @Bean
    public DataSource postgresUsingTestContainer(){
        var container = new PostgreSQLContainer<>("postgres:latest")
                .withDatabaseName("testdb")
                .withUsername("test")
                .withPassword("test");
            container.start();
            var ds = ((HikariDataSource) DataSourceBuilder.create().url(container.getJdbcUrl())
                    .username(container.getUsername())
                    .password(container.getPassword())
                    .build());
            ds.setMaxLifetime(5000);
            cleanmigrate(ds);
            return ds;
    }

    public void cleanmigrate(DataSource ds){

        var fly = Flyway.configure()
                .dataSource(ds)
                .locations("/postgresql")
                .cleanDisabled(false)
                .load();
        fly.clean();
        fly.migrate();
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/testdata/data.sql"));
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

}
