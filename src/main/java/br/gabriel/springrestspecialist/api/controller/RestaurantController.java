package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	public Restaurant findById(@PathVariable Integer id) {
		return repository.findOrFail(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurant save(@RequestBody @Valid Restaurant restaurant) {
		return service.save(restaurant);
	}
	
	@PutMapping("/{id}")
	public Restaurant save(@PathVariable Integer id, @RequestBody @Valid Restaurant restaurant) {
		Restaurant current = repository.findOrFail(id);
		BeanUtils.copyProperties(restaurant, current, "id", "paymentMethods", "address", "createdAt", "products");
			
		return service.save(current);
	}
}