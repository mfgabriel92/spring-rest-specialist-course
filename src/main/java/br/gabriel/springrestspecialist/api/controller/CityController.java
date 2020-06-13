package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

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

import br.gabriel.springrestspecialist.domain.exception.ResourceInUseExeption;
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
		City city = repository.findById(id);

		if (city == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(city);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> save(@RequestBody City city) {
		try {
			city = service.save(city);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (ResourceNotFoundExeption e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<City> save(@PathVariable Integer id, @RequestBody City city) {
		City current = repository.findById(id);

		if (current == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(city, current, "id");
		current = service.save(current);

		return ResponseEntity.ok(current);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		try {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (ResourceNotFoundExeption e) {
			return ResponseEntity.notFound().build();
		} catch (ResourceInUseExeption e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}