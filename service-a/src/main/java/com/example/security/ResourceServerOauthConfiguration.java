package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

import com.example.filter.CustomHttpTraceFilter;

@Configuration
public class ResourceServerOauthConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomHttpTraceFilter filter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		 //@formatter:off
			http.addFilterAfter(filter, SecurityContextPersistenceFilter.class)
			     .authorizeRequests()
			     .requestMatchers(EndpointRequest.to(InfoEndpoint.class, HealthEndpoint.class))
			     .permitAll()
			     .mvcMatchers("/service-a**").access("hasAuthority('SCOPE_authority-a')")
			     .anyRequest()
				 .authenticated()
				 .and()
				 .oauth2ResourceServer()
				 .jwt();
		  //@formatter:on

	}

}
