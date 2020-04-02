package com.example.actuator;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

/**
 * Extend the Actuator /info endpoint.
 * 
 * @author Karthik
 *
 */
@Component
public class AppInfoEndpoint implements InfoContributor {

	@Override
	public void contribute(Builder builder) {
		builder.withDetail("APPLICATION", "SPRING-CLOUD-K8S-EXAMPLES");
		
	}
	
	
	

}
