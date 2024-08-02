package com.ing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EnableCaching
public class IngApplication {

    private static final Logger logger = LoggerFactory.getLogger(IngApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IngApplication.class, args);
        logger.info("App started");
    }
}


