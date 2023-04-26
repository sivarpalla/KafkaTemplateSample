package com.americanfirstfinance.kafkatest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "AFF API", version = "2.0", description = "Kafka Information"))

public class KafkaTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaTestApplication.class, args);
	}
}
