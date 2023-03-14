package com.company.registeruser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegisteruserApplication {
	
	// Creating a logger
		private static final Logger logger = LogManager.getLogger(RegisteruserApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RegisteruserApplication.class, args);
		logger.info("It is a info logger.");
	}

}
