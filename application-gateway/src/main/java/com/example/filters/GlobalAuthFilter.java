package com.example.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.example.dto.LogConfig;

/**
 * Filter for token exchange. 
 * @author Karthik
 *
 */
@Component
public class GlobalAuthFilter extends AbstractGatewayFilterFactory<LogConfig> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalAuthFilter.class);

	@Override
	public GatewayFilter apply(LogConfig config) {

		return (exchange, chain) -> {
			// @formatter:off
			return exchange.getPrincipal()
					.filter(principal -> principal instanceof OAuth2AuthenticationToken)
					.cast(OAuth2AuthenticationToken.class)
					.map(token -> withJwtBearerGrantTypeAuth(exchange, token))
					.defaultIfEmpty(exchange).flatMap(chain::filter);
			// @formatter:on
		};

	}

	private ServerWebExchange withJwtBearerGrantTypeAuth(ServerWebExchange exchange, OAuth2AuthenticationToken accessToken) {
		OidcUser principal = (OidcUser) accessToken.getPrincipal();
		  //Jwt jwt = new Jwt(principal.getIdToken().getTokenValue(), principal.getIdToken().getIssuedAt(), principal.getIdToken().getExpiresAt(), principal.get, principal.getClaims());
	      //JwtAuthenticationToken authRequest = new JwtAuthenticationToken(jwt);
		LOGGER.info("===========GlobalAuthFilter::" +accessToken.getPrincipal());
		return exchange;
	}

}
