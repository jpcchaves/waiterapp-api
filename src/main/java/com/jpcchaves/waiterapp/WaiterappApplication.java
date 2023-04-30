package com.jpcchaves.waiterapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WaiterappApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaiterappApplication.class, args);
	}

}
