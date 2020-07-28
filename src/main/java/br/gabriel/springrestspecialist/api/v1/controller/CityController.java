package br.gabriel.springrestspecialist.api.v1.controller;

import br.gabriel.springrestspecialist.api.mapper.CityMapper;
import br.gabriel.springrestspecialist.api.v1.model.request.CityRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.CityResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.controller.CityDoc;
import br.gabriel.springrestspecialist.core.security.Permission;
import br.gabriel.springrestspecialist.domain.model.City;
import br.gabriel.springrestspecialist.domain.repository.CityRepository;
import br.gabriel.springrestspecialist.domain.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController implements CityDoc {
	@Autowired
	private CityRepository repository;
	
	@Autowired
	private CityService service;
	
	@Autowired
	private CityMapper mapper;

	@Override
	@Permission.City.CanRead
	@GetMapping
	public List<CityResponse> findAll() {
		return mapper.toCollectionModel(repository.findAll());
	}
	
	@Override
	@Permission.City.CanRead
	@GetMapping("{id}")
	public CityResponse findById(@PathVariable Integer id) {
		City city = repository.findOrFail(id);
		CityResponse response = mapper.toModel(city);
		
		response.add(Link.of("http://api.springrestspecialist/v1/cities/" + id));
		response.add(Link.of("http://api.springrestspecialist/v1/cities/", IanaLinkRelations.COLLECTION));
		
		return response;
	}
	
	@Override
	@Permission.Write
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CityResponse save(@RequestBody @Valid CityRequest cityRequest) {
	    City city = mapper.toDomainObject(cityRequest);
		return mapper.toModel(service.save(city));
	}

	@Override
	@Permission.Write
	@PutMapping("{id}")
	public CityResponse save(@PathVariable Integer id, @RequestBody @Valid CityRequest cityRequest) {
		City city = repository.findOrFail(id);
		mapper.copyToDomainObject(cityRequest, city);
		return mapper.toModel(service.save(city));
	}

	@Override
	@Permission.Write
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}
