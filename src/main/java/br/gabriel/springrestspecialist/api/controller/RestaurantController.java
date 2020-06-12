package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.domain.model.Restaurant;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepository;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
	@Autowired
	private RestaurantRepository repository;


	@GetMapping
	public List<Restaurant> findAll() {
		return repository.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<Restaurant> findById(@PathVariable Integer id) {
		Restaurant restaurant = repository.findById(id);

		if (restaurant == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(restaurant);
	}
}