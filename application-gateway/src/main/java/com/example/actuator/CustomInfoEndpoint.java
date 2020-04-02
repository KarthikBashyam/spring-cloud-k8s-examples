package com.example.actuator;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.info.InfoEndpoint;

/**
 * Provide custom Actuator /info Endpoint implementation.
 * 
 * @author Karthik
 *
 */
//@Component
@EndpointWebExtension(endpoint = InfoEndpoint.class)
public class CustomInfoEndpoint {

	@ReadOperation
	public String hello() {
		return "ACTUATOR EXTENSION";
	}

}
