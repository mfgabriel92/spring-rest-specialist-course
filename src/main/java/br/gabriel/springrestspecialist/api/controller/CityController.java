package br.gabriel.springrestspecialist.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.api.exception.ExceptionMessage;
import br.gabriel.springrestspecialist.domain.exception.ApiException;
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
	public City findById(@PathVariable Integer id) {
		return repository.findOrFail(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public City save(@RequestBody City city) {
		try {
			return service.save(city);
		} catch (ResourceNotFoundExeption e) {
			throw new ApiException(e.getMessage());
		}
	}

	@PutMapping("{id}")
	public City save(@PathVariable Integer id, @RequestBody City city) {
		City current = repository.findOrFail(id);
		BeanUtils.copyProperties(city, current, "id");

		try {
			return service.save(current);
		} catch (ResourceNotFoundExeption e) {
			throw new ApiException(e.getMessage());
		}
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> handleApiException(ApiException e) {
		ExceptionMessage exceptionMessage = ExceptionMessage
			.builder()
			.timestamp(LocalDateTime.now())
			.message(e.getMessage())
			.build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessage);
	}
	
	@ExceptionHandler(ResourceNotFoundExeption.class)
	public ResponseEntity<?> handleResourceNotFouncException(ResourceNotFoundExeption e) {
		ExceptionMessage exceptionMessage = ExceptionMessage
			.builder()
			.timestamp(LocalDateTime.now())
			.message(e.getMessage())
			.build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage);
	}
}