package com.example.securitywebflux.config.security;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class ServerPreAuthenticationConverter  implements Function<ServerWebExchange, Mono<Authentication>> {

	private final String credentials = "N/A";

	@Override
	public Mono<Authentication> apply(ServerWebExchange serverWebExchange) {

		ServerHttpRequest httpRequest = serverWebExchange.getRequest();
		String token = httpRequest.getHeaders().getFirst(Constant.X_API_TOKEN);
		PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken = new PreAuthenticatedAuthenticationToken(token, credentials);

		return Mono.just(preAuthenticatedAuthenticationToken);
	}

}
