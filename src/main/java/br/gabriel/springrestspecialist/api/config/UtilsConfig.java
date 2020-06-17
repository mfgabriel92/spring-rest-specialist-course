package br.gabriel.springrestspecialist.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.gabriel.springrestspecialist.api.exception.ExceptionUtils;

@Configuration
public class UtilsConfig {
	@Bean
	public ExceptionUtils exceptionUtils() {
		return new ExceptionUtils();
	}
}
