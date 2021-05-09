package com.columns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ColumnsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColumnsApplication.class, args);
	}

}
