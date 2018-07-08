package com.example.securitywebflux.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SecurityController {

	/**
	 * URL 权限验证部分
	 */
	@GetMapping(value = "/test1")
	public Mono<String> testAnonymous() {
		return Mono.just("testAnonymous");
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/test2")
	public Mono<String> testRoleUser() {
		return Mono.just("testRoleUSER");
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/test3")
	public Mono<String> testRoleAdmin() {
		return Mono.just("testRoleAdmin");
	}

	@GetMapping(value = "/test4")
	public Mono<String> testRoleAdmin2() {
		return Mono.just("testRoleAdmin2 by url");
	}

	@PreAuthorize("hasAuthority('user:select')")
	@GetMapping(value = "/test5")
	public Mono<String> testUserAuthority() {
		return Mono.just("testUserAuthoritySelect");
	}

	@PreAuthorize("hasAuthority('user:add')")
	@GetMapping(value = "/test6")
	public Mono<String> testUserAuthority2() {
		return Mono.just("testUserAuthorityADD");
	}

	@GetMapping(value = "/test7")
	public Mono<String> testUserAuthorityAdmin() {
		return Mono.just("testUserAuthorityUpdate by url");
	}

}
