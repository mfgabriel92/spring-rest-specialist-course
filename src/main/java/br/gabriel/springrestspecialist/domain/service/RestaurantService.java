package br.gabriel.springrestspecialist.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.exception.ApiException;
import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;
import br.gabriel.springrestspecialist.domain.model.City;
import br.gabriel.springrestspecialist.domain.model.Kitchen;
import br.gabriel.springrestspecialist.domain.model.PaymentMethod;
import br.gabriel.springrestspecialist.domain.model.Restaurant;
import br.gabriel.springrestspecialist.domain.repository.CityRepository;
import br.gabriel.springrestspecialist.domain.repository.KitchenRepository;
import br.gabriel.springrestspecialist.domain.repository.PaymentMethodRepository;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepository;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantRepository repository;
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private PaymentMethodRepository paymentMethodRepository;
	
	@Transactional
	public Restaurant save(Restaurant restaurant) {
		try {
		    Integer kitchenId = restaurant.getKitchen().getId();
	        Kitchen kitchen = kitchenRepository.findOrFail(kitchenId);
	        
	        Integer cityId = restaurant.getAddress().getCity().getId();
	        City city = cityRepository.findOrFail(cityId);
	        
	        restaurant.setKitchen(kitchen);
	        restaurant.getAddress().setCity(city);
	        
	        return repository.save(restaurant);
		} catch (ResourceNotFoundExeption e) {
		    throw new ApiException(e.getMessage());
		}
	}
	
	@Transactional
	public void activate(Integer id) {
	    repository.findOrFail(id).activate();
	}
	
	@Transactional
    public void deactivate(Integer id) {
        repository.findOrFail(id).deactivate();
    }
	
	@Transactional
    public void addPaymentMethod(Integer id, Integer paymentMethodId) {
        Restaurant restaurant = repository.findOrFail(id);
        PaymentMethod paymentMethod = paymentMethodRepository.findOrFail(paymentMethodId);
        restaurant.addPaymentMethod(paymentMethod);
    }
	
	@Transactional
    public void removePaymentMethod(Integer id, Integer paymentMethodId) {
        Restaurant restaurant = repository.findOrFail(id);
        PaymentMethod paymentMethod = paymentMethodRepository.findOrFail(paymentMethodId);
        restaurant.removePaymentMethod(paymentMethod);
    }
	
	@Transactional
    public void open(Integer id) {
        repository.findOrFail(id).open();
    }
    
    @Transactional
    public void close(Integer id) {
        repository.findOrFail(id).close();
    }
}
