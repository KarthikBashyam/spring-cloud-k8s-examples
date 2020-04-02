package com.example.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import com.example.dto.LogConfig;

/**
 * Gateway Filter for routing.
 * 
 * @author Karthik
 *
 */
@Component
public class LoggingGatewayFilter extends AbstractGatewayFilterFactory<LogConfig> {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingGatewayFilter.class);

	@Override
	public GatewayFilter apply(LogConfig config) {

		return (exchange, chain) -> {
			LOGGER.info("Inside LoggingGatewayFilter:" + exchange.getRequest().getHeaders().getOrEmpty("Authorization"));
			return chain.filter(exchange);
		};
	}

}
