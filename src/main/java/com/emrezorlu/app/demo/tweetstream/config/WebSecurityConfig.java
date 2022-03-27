package com.emrezorlu.app.demo.tweetstream.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/message" + "/**", "/statistics" + "/**",
				// -- Swagger UI v2
				"/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
				"/configuration/security", "/swagger-ui/index.html", "/webjars/**",
				// -- Swagger UI v3
				"/v3/api-docs/**", "/swagger-ui/**").permitAll().anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
