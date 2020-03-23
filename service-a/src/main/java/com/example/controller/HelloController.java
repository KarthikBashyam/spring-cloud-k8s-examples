package com.example.controller;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.config.ApplicationConfig;

@RestController
public class HelloController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private Environment environment;

	@Autowired
	private ApplicationConfig config;
	
	@Autowired
	@Qualifier("loadBalancedRestTemplate")
	private RestTemplate restTemplate;
	
	@GetMapping(path = "/service-a")
	public String hello() {
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		String serviceBResponse = restTemplate.getForObject("lb://service-b-service/service-b", String.class);
		LOGGER.info("Response from SERVICE-B to SERVICE-A :"+serviceBResponse);
		return "Hello from service-a:" + config.getName() + ":" + ip;
	}

}