package com.example.intershipmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IntershipManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntershipManagementApplication.class, args);
	}

}
