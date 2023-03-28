package com.bina.az.binaazdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BinaAzDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(BinaAzDataApplication.class, args);
    }

}
