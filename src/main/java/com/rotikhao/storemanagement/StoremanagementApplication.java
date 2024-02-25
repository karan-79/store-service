package com.rotikhao.storemanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class StoremanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoremanagementApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(JdbcConnectionDetails ds){
		return args -> {
			System.out.println(ds.toString());
		};
	}

}
