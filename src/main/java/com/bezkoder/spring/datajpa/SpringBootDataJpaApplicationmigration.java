package com.bezkoder.spring.datajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:${CONFIG_MODE}/application.properties")
public class SpringBootDataJpaApplicationmigration {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplicationmigration.class, args);
	}

}
