//package com.example.securitywebflux.filter;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
//
///**
// * Spring5 Filter
// */
//@Component
//public class Test02Filter implements WebFilter {
//
//	private Logger logger = LoggerFactory.getLogger(Test02Filter.class);
//
//	@Override
//	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//
//		logger.info("Test02Filter Test02Filter Test02Filter filter");
//
//		return chain.filter(exchange);
//	}
//
//
//}
