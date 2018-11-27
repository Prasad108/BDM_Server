package com.app.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "com.app" })
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class})
@SpringBootApplication
public class BookDistributionAccountsManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookDistributionAccountsManagementApplication.class, args);
	}
}
