package com.postexpress.Postrexpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class PostrexpressApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostrexpressApplication.class, args);
	}

}
