package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping(path = "/service-a")
	public String hello() {
		return "Hello from service-a";
	}
	
	@GetMapping(path = "/")
	public String welcome() {
		return "Welcome from service-a";
	}

}
