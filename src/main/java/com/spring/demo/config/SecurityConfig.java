package com.spring.demo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.antMatcher("/students/**").authorizeRequests()
		.antMatchers("/students/**").hasAnyRole("USER","ADMIN")
		.antMatchers("/students/admin/**").hasRole("ADMIN")
				.and().httpBasic().and().csrf().disable();
		return http.build();

	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build();
		UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("ADMIN", "USER")
				.build();
		return new InMemoryUserDetailsManager(List.of(user, admin));
	}

}
