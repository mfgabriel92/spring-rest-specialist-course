package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

import br.gabriel.springrestspecialist.api.model.mapper.CuisineMapper;
import br.gabriel.springrestspecialist.api.model.request.CuisineRequest;
import br.gabriel.springrestspecialist.api.model.response.CuisineResponse;
import br.gabriel.springrestspecialist.domain.model.Cuisine;
import br.gabriel.springrestspecialist.domain.repository.CuisineRepository;
import br.gabriel.springrestspecialist.domain.service.CuisineService;

@RestController
@RequestMapping("/cuisines")
public class CuisineController {
	@Autowired
	private CuisineRepository repository;

	@Autowired
	private CuisineService service;
	
	@Autowired
	private CuisineMapper mapper;

	@GetMapping
	public Page<CuisineResponse> findAll(Pageable pageable) {
	    Page<Cuisine> pagedCuisines = repository.findAll(pageable);
        List<CuisineResponse> cuisines = mapper.toCollectionModel(pagedCuisines.getContent());
        return new PageImpl<>(cuisines, pageable, pagedCuisines.getTotalElements());
	}

	@GetMapping("{id}")
	public CuisineResponse findById(@PathVariable Integer id) {
		return mapper.toModel(repository.findOrFail(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CuisineResponse save(@RequestBody @Valid CuisineRequest cuisineRequest) {
	    Cuisine cuisine = mapper.toDomainObject(cuisineRequest);
		return mapper.toModel(service.save(cuisine));
	}

	@PutMapping("{id}")
	public CuisineResponse save(@PathVariable Integer id, @RequestBody @Valid CuisineRequest cuisineRequest) {
	    Cuisine cuisine = mapper.toDomainObject(cuisineRequest);
		Cuisine current = repository.findOrFail(id);
		BeanUtils.copyProperties(cuisine, current, "id");
		
		return mapper.toModel(service.save(current));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}