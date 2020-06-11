package br.gabriel.springrestspecialist.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.gabriel.springrestspecialist.SpringRestSpecialistApplication;
import br.gabriel.springrestspecialist.domain.model.Restaurant;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepository;

public class RestaurantQueryMain {
	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new SpringApplicationBuilder(SpringRestSpecialistApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
		
		RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);
		
		List<Restaurant> restaurants = restaurantRepository.findAll();
		
		for (Restaurant restaurant : restaurants) {
			System.out.println(String.format("%s - %f - %s", restaurant.getName(), restaurant.getShippingFee(), restaurant.getKitchen().getName()));
		}
	}
}
