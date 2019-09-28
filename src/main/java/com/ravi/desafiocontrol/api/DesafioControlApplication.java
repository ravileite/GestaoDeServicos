package com.ravi.desafiocontrol.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DesafioControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioControlApplication.class, args);
	}

}
