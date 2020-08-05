package com.onlineMarket.ShoppingCartService;

import com.onlineMarket.ShoppingCartService.Models.ActiveParameters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class ShoppingCartServiceApplication {

	@Bean
	public ActiveParameters activeParameters(){ return new ActiveParameters(); }

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartServiceApplication.class, args);

	}
}
