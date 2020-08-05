package com.OnlineMarket.AdminService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class AdminServiceApplication {

	@Bean
	public ActiveParameters activeParameters(){return new ActiveParameters(); }

	public static void main(String[] args) {
		SpringApplication.run(AdminServiceApplication.class, args);
	}

}
