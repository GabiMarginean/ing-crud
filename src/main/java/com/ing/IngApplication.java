package com.ing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class) // Security not implemented yet
@EnableCaching
public class IngApplication {

    private static final Logger logger = LoggerFactory.getLogger(IngApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IngApplication.class, args);
        logger.info("App started");
    }
}


