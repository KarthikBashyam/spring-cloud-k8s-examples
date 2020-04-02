package com.example.controller;

import java.net.InetAddress;
import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.ApplicationConfig;

@RestController
public class HelloController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private Environment environment;

	@Autowired
	private ApplicationConfig config;
	
	/*@Autowired
	@Qualifier("loadBalancedRestTemplate")
	private RestTemplate restTemplate;*/
	
	@GetMapping(path = "/service-a")
	public String hello(Principal token) {
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}		
		return "Hello from service-a:" + token;
	}
	
	@GetMapping(path="/service-a/showjwt") 
	public JwtAuthenticationToken hello(@AuthenticationPrincipal JwtAuthenticationToken jwt) {
		return jwt;
	}


}