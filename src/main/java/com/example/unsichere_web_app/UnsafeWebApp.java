package com.example.unsichere_web_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class UnsafeWebApp {

	public static void main(String[] args) {
		SpringApplication.run(UnsafeWebApp.class, args);
	}

}