package com.example.springbootkata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.springbootkata")
public class SpringBootKataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKataApplication.class, args);
    }

}
