package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import br.gabriel.springrestspecialist.api.controller.documentation.CityDoc;
import br.gabriel.springrestspecialist.api.model.mapper.CityMapper;
import br.gabriel.springrestspecialist.api.model.request.CityRequest;
import br.gabriel.springrestspecialist.api.model.response.CityResponse;
import br.gabriel.springrestspecialist.domain.model.City;
import br.gabriel.springrestspecialist.domain.repository.CityRepository;
import br.gabriel.springrestspecialist.domain.service.CityService;

@RestController
@RequestMapping("/cities")
public class CityController implements CityDoc {
	@Autowired
	private CityRepository repository;
	
	@Autowired
	private CityService service;
	
	@Autowired
	private CityMapper mapper;

	@GetMapping
	public List<CityResponse> findAll() {
		return mapper.toCollectionModel(repository.findAll());
	}
	
	@GetMapping("{id}")
	public CityResponse findById(@PathVariable Integer id) {
		return mapper.toModel(repository.findOrFail(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CityResponse save(@RequestBody @Valid CityRequest cityRequest) {
	    City city = mapper.toDomainObject(cityRequest);
		return mapper.toModel(service.save(city));
	}

	@PutMapping("{id}")
	public CityResponse save(@PathVariable Integer id, @RequestBody @Valid CityRequest cityRequest) {
		City city = repository.findOrFail(id);
		mapper.copyToDomainObject(cityRequest, city);
		return mapper.toModel(service.save(city));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}