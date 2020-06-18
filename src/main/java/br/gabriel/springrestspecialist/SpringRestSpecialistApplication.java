package br.gabriel.springrestspecialist;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.gabriel.springrestspecialist.infrastructure.repository.BaseJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseJpaRepositoryImpl.class)
public class SpringRestSpecialistApplication {
	public static void main(String[] args) {
	    TimeZone.setDefault(TimeZone.getTimeZone("UTC  "));
		SpringApplication.run(SpringRestSpecialistApplication.class, args);
	}
}