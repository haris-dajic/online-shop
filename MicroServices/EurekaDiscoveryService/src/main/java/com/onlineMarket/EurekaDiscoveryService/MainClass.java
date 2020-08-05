package com.onlineMarket.EurekaDiscoveryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
@EnableEurekaServer
public class MainClass {

	public static void main(String[] args) {
		SpringApplication.run(MainClass.class, args);
		System.out.println("Eureka service running...");
	}

}
