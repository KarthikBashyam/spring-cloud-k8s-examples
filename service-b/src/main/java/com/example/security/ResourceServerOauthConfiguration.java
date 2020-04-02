package com.example.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ResourceServerOauthConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		// @formatter:off
			http.authorizeRequests().mvcMatchers("/service-b**").access("hasAuthority('SCOPE_authority-b')")
			     .anyRequest()
				 .authenticated()
				 .and()
				 .oauth2ResourceServer()
				 .jwt();
		// @formatter:on

	}

}
