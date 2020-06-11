package br.gabriel.springrestspecialist.domain.repository;

import java.util.List;

import br.gabriel.springrestspecialist.domain.model.Restaurant;

public interface RestaurantRepository {
	List<Restaurant> findAll();
	
	Restaurant findById(Integer id);
	
	Restaurant save(Restaurant restaurant);
	
	void delete(Restaurant restaurant);
}
