package br.gabriel.springrestspecialist.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional
	public Restaurant save(Restaurant restaurant) {
		Integer kitchenId = restaurant.getKitchen().getId();
		Kitchen kitchen = kitchenRepository.findOrFail(kitchenId);
		
		restaurant.setKitchen(kitchen);
		
		return repository.save(restaurant);
	}
	
	@Transactional
	public void activate(Integer id) {
	    repository.findOrFail(id).activate();
	}
	
	@Transactional
    public void deactivate(Integer id) {
        repository.findOrFail(id).deactivate();
    }
}
