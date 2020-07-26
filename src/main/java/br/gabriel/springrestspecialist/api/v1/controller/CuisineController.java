package br.gabriel.springrestspecialist.api.v1.controller;

import br.gabriel.springrestspecialist.api.mapper.CuisineMapper;
import br.gabriel.springrestspecialist.api.v1.model.request.CuisineRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.CuisineResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.controller.CuisineDoc;
import br.gabriel.springrestspecialist.core.security.Permission;
import br.gabriel.springrestspecialist.domain.model.Cuisine;
import br.gabriel.springrestspecialist.domain.repository.CuisineRepository;
import br.gabriel.springrestspecialist.domain.service.CuisineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/v1/cuisines", produces = MediaType.APPLICATION_JSON_VALUE)
public class CuisineController implements CuisineDoc {
	@Autowired
	private CuisineRepository repository;

	@Autowired
	private CuisineService service;
	
	@Autowired
	private CuisineMapper mapper;

	@Override
	@Permission.Authenticated
    @GetMapping
	public Page<CuisineResponse> findAll(Pageable pageable) {
		log.info("Querying cuisines - initializing");
	    Page<Cuisine> pagedCuisines = repository.findAll(pageable);
		List<CuisineResponse> cuisines = mapper.toCollectionModel(pagedCuisines.getContent());
		log.info("Querying cuisines - finalizing");
        return new PageImpl<>(cuisines, pageable, pagedCuisines.getTotalElements());
	}

	@Override
	@Permission.Authenticated
	@GetMapping("{id}")
	public CuisineResponse findById(@PathVariable Integer id) {
		return mapper.toModel(repository.findOrFail(id));
	}

	@Override
	@Permission.Write
    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CuisineResponse save(@RequestBody @Valid CuisineRequest cuisineRequest) {
	    Cuisine cuisine = mapper.toDomainObject(cuisineRequest);
		return mapper.toModel(service.save(cuisine));
	}

	@Override
	@Permission.Write
    @PutMapping("{id}")
	public CuisineResponse save(@PathVariable Integer id, @RequestBody @Valid CuisineRequest cuisineRequest) {
	    Cuisine cuisine = mapper.toDomainObject(cuisineRequest);
		Cuisine current = repository.findOrFail(id);
		BeanUtils.copyProperties(cuisine, current, "id");
		
		return mapper.toModel(service.save(current));
	}

	@Override
	@Permission.Delete
    @DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}
