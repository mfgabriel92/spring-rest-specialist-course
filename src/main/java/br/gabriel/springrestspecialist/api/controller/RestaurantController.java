package br.gabriel.springrestspecialist.api.controller;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;
import br.gabriel.springrestspecialist.domain.model.Restaurant;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepository;
import br.gabriel.springrestspecialist.domain.service.RestaurantService;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
	@Autowired
	private RestaurantRepository repository;

	@Autowired
	private RestaurantService service;

	@GetMapping
	public List<Restaurant> findAll() {
		return repository.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<Restaurant> findById(@PathVariable Integer id) {
		Optional<Restaurant> restaurant = repository.findById(id);

		if (restaurant.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(restaurant.get());
	}
	
	@GetMapping("name")
	public List<Restaurant> findByNameContaining(@RequestParam String name) {
		return repository.findByNameContaining(name);
	}
	
	@GetMapping("first")
	public Restaurant findFirstByNameContaining(String name) {
		return repository.findFirstByNameContaining(name).orElse(null);
	}
	
	@GetMapping("shipping-fees")
	public List<Restaurant> findByShippingFeeBetween(BigDecimal minFee, BigDecimal maxFee) {
		return repository.findByShippingFeeBetween(minFee, maxFee);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
		try {
			restaurant = service.save(restaurant);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
		} catch (ResourceNotFoundExeption e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> save(@PathVariable Integer id, @RequestBody Restaurant restaurant) {
		try {
			Optional<Restaurant> current = repository.findById(id);
			
			if (current.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			
			BeanUtils.copyProperties(restaurant, current, "id");
			Restaurant updatedRestaurant = service.save(current.get());
			
			return ResponseEntity.ok().body(updatedRestaurant);
		} catch (ResourceNotFoundExeption e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> save(@PathVariable Integer id, @RequestBody Map<String, Object> fields) {
		Optional<Restaurant> current = repository.findById(id);

		if (current.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		merge(fields, current.get());
		
		return save(id, current.get());
	}

	private void merge(Map<String, Object> fields, Restaurant source) {
		ObjectMapper mapper = new ObjectMapper();
		Restaurant restaurant = mapper.convertValue(fields, Restaurant.class);
		
		fields.forEach((fieldName, fieldValue) -> {
			Field field = ReflectionUtils.findField(Restaurant.class, fieldName);
			field.setAccessible(true);
			
			Object value = ReflectionUtils.getField(field, restaurant);
			
			ReflectionUtils.setField(field, source, value);
		});
	}
}