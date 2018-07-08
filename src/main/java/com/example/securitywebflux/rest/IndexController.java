package com.example.securitywebflux.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class IndexController {

	@GetMapping("/hello_world")
	public Mono<String> sayHelloWorld() {
		return Mono.just("Hello World");
	}

	
}
