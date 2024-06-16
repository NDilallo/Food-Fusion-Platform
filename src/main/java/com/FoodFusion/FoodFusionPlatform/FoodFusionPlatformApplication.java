package com.foodFusion.foodFusionPlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class FoodFusionPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodFusionPlatformApplication.class, args);
	}

}
