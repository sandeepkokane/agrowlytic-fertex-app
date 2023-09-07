package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AgrowlyticFertexAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgrowlyticFertexAppApplication.class, args);
    }

}
