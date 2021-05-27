package com.estupinia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EstupiniaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstupiniaApplication.class, args);
	}

}
