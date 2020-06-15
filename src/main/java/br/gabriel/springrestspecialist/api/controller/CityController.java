package br.gabriel.springrestspecialist.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;
import br.gabriel.springrestspecialist.domain.model.City;
import br.gabriel.springrestspecialist.domain.repository.CityRepository;
import br.gabriel.springrestspecialist.domain.service.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {
	@Autowired
	private CityRepository repository;
	
	@Autowired
	private CityService service;

	@GetMapping
	public List<City> findAll() {
		return repository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<City> findById(@PathVariable Integer id) {
		Optional<City> city = repository.findById(id);

		if (city.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(city.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> save(@RequestBody City city) {
		try {
			city = service.save(city);
			return ResponseEntity.status(HttpStatus.CREATED).body(city);
		} catch (ResourceNotFoundExeption e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<City> save(@PathVariable Integer id, @RequestBody City city) {
		Optional<City> current = repository.findById(id);

		if (current.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(city, current.get(), "id");
		City updatedCity  = service.save(current.get());

		return ResponseEntity.ok(updatedCity);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}