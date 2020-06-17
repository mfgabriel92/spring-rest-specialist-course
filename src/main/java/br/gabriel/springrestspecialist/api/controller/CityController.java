package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	public City findById(@PathVariable Integer id) {
		return repository.findOrFail(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public City save(@RequestBody @Valid City city) {
		return service.save(city);
	}

	@PutMapping("{id}")
	public City save(@PathVariable Integer id, @RequestBody @Valid City city) {
		City current = repository.findOrFail(id);
		BeanUtils.copyProperties(city, current, "id");

		return service.save(current);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}