package com.example.gateway;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.security.oauth2.gateway.TokenRelayGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.LogConfig;
import com.example.filters.GlobalAuthFilter;
import com.example.filters.LoggingGatewayFilter;
@SpringBootApplication(scanBasePackages = "com.example.*")
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
	public RouteLocator myRoutes(RouteLocatorBuilder builder, TokenRelayGatewayFilterFactory relayFilter,
			LoggingGatewayFilter filter, GlobalAuthFilter global) {
		//Spring Cloud Kubernetes uses ribbon for load balancing
		// @formatter:off
		return builder.routes().route(p -> p.path("/service-a**","/service-a/showjwt**").filters(
				f -> f.filter(relayFilter.apply())
				      .filter(filter.apply(new LogConfig("Test Filter")))
				      .filter(global.apply(new LogConfig("Auth Filter"))))
				      .uri("lb://service-a-service"))				      
				 .route(p -> p.path("/service-b**").filters(
				f -> f.filter(relayFilter.apply()))
					  .uri("lb://service-b-service"))
				      .build();
		// @formatter:on
	}
		

	@Bean
	CommandLineRunner startup() {
		return args -> {
			logger.info("============= STARTED GATEWAY LIST SERVICES================");
			discoveryClient.getServices().forEach(svc -> logger.info(svc));
		};
	}

	// For Actuator httptrace endpoint.
	@Bean
	HttpTraceRepository trace() {
		return new InMemoryHttpTraceRepository();
	}

	@GetMapping(path = "/gateway")
	public String hello(OAuth2AuthenticationToken  accessToken) {
		return "Application Gateway:" + accessToken;
	}
	
	@GetMapping("/") 
	public String homw(HttpRequest httpRequest) {
		return "home";
	}
	
}
