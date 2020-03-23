package com.example.gateway;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ApplicationGatewayMain {
	
	private static final Log logger = LogFactory.getLog(ApplicationGatewayMain.class);

	@Autowired
	private DiscoveryClient discoveryClient;

	public static void main(String[] args) {
		SpringApplication.run(ApplicationGatewayMain.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/service-a**").uri("lb://service-a-service"))
				.route(p -> p.path("/service-b**").uri("lb://service-b-service"))
				.build();
	}
	
	@Bean
	CommandLineRunner startup() {
		return args -> {
			logger.info("============= STARTED GATEWAY LIST SERVICES================");
			discoveryClient.getServices().forEach(s -> logger.info(s));
		};
	}
	
	
}
