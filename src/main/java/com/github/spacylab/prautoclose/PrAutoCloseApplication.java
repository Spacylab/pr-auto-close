package com.github.spacylab.prautoclose;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PrAutoCloseApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrAutoCloseApplication.class, args);
	}

}
