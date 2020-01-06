package de.siewares.vfl;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableWebMvc
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, "--debug");
    }

}