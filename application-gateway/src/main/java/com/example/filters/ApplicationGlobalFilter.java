package com.example.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * Global Filters will be applied for all the routes.
 * 
 * @author Karthik
 *
 */
@Component
public class ApplicationGlobalFilter implements GlobalFilter {
 
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationGlobalFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		LOGGER.info("Inside the Global Filter....");
		return chain.filter(exchange);
	}

}
