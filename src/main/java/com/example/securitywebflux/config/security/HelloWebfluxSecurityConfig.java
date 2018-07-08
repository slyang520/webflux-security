package com.example.securitywebflux.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@Configuration
@EnableReactiveMethodSecurity
public class HelloWebfluxSecurityConfig {

	@Bean
	public MapReactiveUserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("user")
				.roles("USER")
				.authorities("user:select", "user:update")
				.build();

		return new MapReactiveUserDetailsService(user);
	}

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {


		// Disable default security.
		http.httpBasic().disable()
				.formLogin().disable()
				.csrf().disable()
				.logout().disable();

		// Authentication
		http
				.authorizeExchange()
				.pathMatchers("/test1").permitAll()
				.pathMatchers("/test4").hasRole("ADMIN")
				.pathMatchers("/test7").hasAuthority("user:update")
				.anyExchange().authenticated();

		// Add custom security.
		http.authenticationManager(reactiveAuthenticationManager());
		//http.securityContextRepository(this.securityContextRepository);

		http.addFilterAt(preAuthenticationWebFilter(), SecurityWebFiltersOrder.HTTP_BASIC);

		return http.build();

	}

	@Bean
	public ReactiveAuthenticationManager reactiveAuthenticationManager() {

		return new ReactiveAuthenticationManager() {
			@Override
			public Mono<Authentication> authenticate(Authentication authentication) {

				if (authentication instanceof PreAuthenticatedAuthenticationToken) {
					// todo
					//PreAuthenticatedAuthenticationToken token = new  PreAuthenticatedAuthenticationToken();
					authentication.setAuthenticated(true);
				}
				return Mono.just(authentication);
			}
		};
	}

	@Bean
	public AuthenticationWebFilter preAuthenticationWebFilter() {

		AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(
				reactiveAuthenticationManager());
		authenticationWebFilter.setAuthenticationConverter(serverPreAuthenticationConverter());

		return authenticationWebFilter;
	}

	@Bean
	public ServerPreAuthenticationConverter serverPreAuthenticationConverter() {
		return new ServerPreAuthenticationConverter();
	}


}
