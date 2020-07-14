package br.gabriel.springrestspecialist.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			httpBasic().and()
				.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/v1/users/**").permitAll()
					.anyRequest().authenticated().and()
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.cors().and()
				.csrf().disable();
	}
}
