package br.gabriel.springrestspecialist.domain.service;

import br.gabriel.springrestspecialist.domain.exception.ApiException;
import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;
import br.gabriel.springrestspecialist.domain.model.*;
import br.gabriel.springrestspecialist.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantRepository repository;
	
	@Autowired
	private CuisineRepository cuisineRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private PaymentMethodRepository paymentMethodRepository;
	
	@Transactional
	public Restaurant save(Restaurant restaurant) {
		try {
		    Integer cuisineId = restaurant.getCuisine().getId();
	        Cuisine cuisine = cuisineRepository.findOrFail(cuisineId);
	        
	        Integer cityId = restaurant.getAddress().getCity().getId();
	        City city = cityRepository.findOrFail(cityId);
	        
	        restaurant.setCuisine(cuisine);
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
    
    @Transactional
    public void addUser(Integer id, Integer userId) {
        Restaurant restaurant = repository.findOrFail(id);
        User user = userRepository.findOrFail(userId);
        restaurant.addUser(user);
    }
    
    @Transactional
    public void removeUser(Integer id, Integer userId) {
        Restaurant restaurant = repository.findOrFail(id);
        User user = userRepository.findOrFail(userId);
        restaurant.removeUser(user);
    }
}
