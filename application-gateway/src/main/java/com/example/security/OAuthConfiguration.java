package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class OAuthConfiguration {

	@Bean
	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
		//@formatter:off
		http
        .authorizeExchange()
            .pathMatchers("/").permitAll()
            .anyExchange().authenticated()
            .and()            
        .oauth2Login()
            .and()
        .oauth2ResourceServer()
            .jwt();
		http.csrf().disable();
		//@formatter:on
		return http.build();
	}

}
