package fi.tuni.tastingapp;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"fi.tuni.tastingapp",
		"fi.tuni.tastingapp.configuration",
		"fi.tuni.tastingapp.controller",
		"fi.tuni.tastingapp.entity",
		"fi.tuni.tastingapp.repository"})
public class Application {
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}