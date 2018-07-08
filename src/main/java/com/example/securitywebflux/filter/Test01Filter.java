package com.example.securitywebflux.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


/**
 * Spring5 Filter
 */
@Component
public class Test01Filter implements WebFilter {

	private Logger logger = LoggerFactory.getLogger(Test01Filter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {


		logger.info("Test01Filter Test01Filter Test01Filter filter");

		ServerHttpRequest request = exchange.getRequest();
		String tokenValue = request.getHeaders().getFirst("token");

		if (null == tokenValue) {
			ServerHttpRequest authErrorReq = request.mutate().path("/auth/error").build();
			ServerWebExchange authErrorExchange = exchange.mutate().request(authErrorReq).build();


			return chain.filter(exchange);
		} else {
			return chain.filter(exchange);
		}
	}


}
