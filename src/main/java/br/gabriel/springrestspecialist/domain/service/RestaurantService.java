package br.gabriel.springrestspecialist.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;
import br.gabriel.springrestspecialist.domain.model.Kitchen;
import br.gabriel.springrestspecialist.domain.model.Restaurant;
import br.gabriel.springrestspecialist.domain.repository.KitchenRepository;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepository;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantRepository repository;
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	public Restaurant save(Restaurant restaurant) {
		Integer kitchenId = restaurant.getKitchen().getId();
		Kitchen kitchen = kitchenRepository.findById(kitchenId);
		
		if (kitchen == null) {
			throw new ResourceNotFoundExeption(String.format("Kitchen ID %d does not exist", kitchenId));
		}
		
		restaurant.setKitchen(kitchen);
		
		return repository.save(restaurant);
	}
}
