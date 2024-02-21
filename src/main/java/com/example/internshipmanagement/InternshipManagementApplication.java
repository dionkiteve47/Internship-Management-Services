package com.example.internshipmanagement;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InternshipManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternshipManagementApplication.class, args);
	}
	public class WebMvcConfig {
		// Configuration for Multipart support
	}

}
