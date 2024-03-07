package com.nikhil.springBoot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class MyWebSecurityClass {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		/**
		 * formLogin - to have own login form. 
		 * permitAll - to call the page
		 * logoutRequestMatcher - tells for which path the user logs out
		 * loginPage - Login page will be accessed through this endpoint. Create a controller method for this.
		 * loginProcessingUrl - This endpoint will be mapped internally. This URL will be our Login form post action.
		 * defaultSuccessUrl - Redirects to this URL after successful login
		 * usernameParameter & passwordParameter - To set Custom login fields
		 * authorizeHttpRequests - Allows the mentioned path without authentication
		 */
		
		http
		.csrf(c -> c.disable())
		.authorizeHttpRequests(request -> request
				.requestMatchers("/registration").permitAll()
				.requestMatchers("/WEB-INF/jsp/**").permitAll()
				.anyRequest().authenticated())

		.formLogin(login -> login
				.loginPage("/login").permitAll()
				.loginProcessingUrl("/login")
				.usernameParameter("email")
				.passwordParameter("password")
				.defaultSuccessUrl("/home")
				.failureUrl("/login?error=true")
				)

		.logout((logout) -> logout
				.invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/logout-success").permitAll());

		return http.build();
	}
	
}
