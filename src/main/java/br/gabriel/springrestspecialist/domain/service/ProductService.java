package br.gabriel.springrestspecialist.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;
import br.gabriel.springrestspecialist.domain.model.Product;
import br.gabriel.springrestspecialist.domain.model.Restaurant;
import br.gabriel.springrestspecialist.domain.repository.ProductRepository;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;
	
	@Autowired
    private RestaurantRepository restaurantRepository;
	
	public List<Product> findAll(Integer restaurantId) {
	    return restaurantRepository.findOrFail(restaurantId).getProducts();
	}
	
	@Transactional
	public Product save(Integer restaurantId, Product product) {
	    Restaurant restaurant = restaurantRepository.findOrFail(restaurantId);
	    restaurant.getProducts().add(product);

	    product.setRestaurant(restaurant);
	    return repository.save(product);
	}

	@Transactional
	public void deleteById(Integer id) {
		repository.deleteOrFail(id);
	}
	
	public Product findOrFail(Integer id, Integer restaurantId) {
	    Restaurant restaurant = restaurantRepository.findOrFail(restaurantId);
	    return repository
	        .findById(id, restaurant.getId())
	        .orElseThrow(() -> new ResourceNotFoundExeption(String.format("The product ID %d was not found", id)));
	}
}