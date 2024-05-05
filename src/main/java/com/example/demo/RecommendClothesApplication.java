package com.example.demo;

import com.example.demo.service.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class RecommendClothesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecommendClothesApplication.class, args);
	}

}
