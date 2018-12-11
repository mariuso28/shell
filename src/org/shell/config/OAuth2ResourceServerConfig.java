package org.shell.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
		http
			.antMatcher("/api/**")
			.authorizeRequests()
			.antMatchers(
					"/api/a/**",
					"/api/anon/**"
					)
			.permitAll()			
			.antMatchers(
					"/api/punter/**"
					)
			.access("hasAnyRole('ROLE_PUNTER')")
			.antMatchers(
					"/api/admin/**"
					)
			.access("hasAnyRole('ROLE_ADMIN')")
		
		;
    }

}