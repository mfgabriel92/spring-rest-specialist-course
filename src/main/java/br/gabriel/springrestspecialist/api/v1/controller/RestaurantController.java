package br.gabriel.springrestspecialist.api.v1.controller;

import br.gabriel.springrestspecialist.api.mapper.RestaurantMapper;
import br.gabriel.springrestspecialist.api.mapper.RestaurantSummaryMapper;
import br.gabriel.springrestspecialist.api.v1.model.request.RestaurantRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.RestaurantResponse;
import br.gabriel.springrestspecialist.api.v1.model.response.RestaurantSummaryResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.controller.RestaurantDoc;
import br.gabriel.springrestspecialist.core.security.Permission;
import br.gabriel.springrestspecialist.domain.model.Restaurant;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepository;
import br.gabriel.springrestspecialist.domain.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController implements RestaurantDoc {
	@Autowired
	private RestaurantRepository repository;

	@Autowired
	private RestaurantService service;
	
	@Autowired
	private RestaurantMapper mapper;
	
	@Autowired
    private RestaurantSummaryMapper summaryMapper;
	
	@Autowired
	private PagedResourcesAssembler<Restaurant> pagedResourcesAssembler;

	@Override
    @GetMapping
	public PagedModel<RestaurantSummaryResponse> findAll(Pageable pageable) {
	    Page<Restaurant> restaurants = repository.findAll(pageable);
	    return pagedResourcesAssembler.toModel(restaurants, summaryMapper);
	}

	@Override
	@GetMapping("{id}")
	public RestaurantResponse findById(@PathVariable Integer id) {
		return mapper.toModel(repository.findOrFail(id));
	}
	
	@Override
	@Permission.Write
    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestaurantResponse save(@RequestBody @Valid RestaurantRequest restaurantRequest) {
	    Restaurant restaurant = mapper.toDomainObject(restaurantRequest);
		return mapper.toModel(service.save(restaurant));
	}
	
	@Override
	@Permission.Write
    @PutMapping("/{id}")
	public RestaurantResponse save(@PathVariable Integer id, @RequestBody @Valid RestaurantRequest restaurantRequest) {
		Restaurant restaurant = repository.findOrFail(id);
		mapper.copyToDomainObject(restaurantRequest, restaurant);
		return mapper.toModel(service.save(restaurant));
	}

	@Override
	@Permission.Write
    @PutMapping("/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@RequestBody List<Integer> ids) {
        service.activate(ids);
    }

	@Override
	@Permission.Write
    @PutMapping("/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@RequestBody List<Integer> ids) {
        service.deactivate(ids);
    }
	
	@Override
	@Permission.Restaurant.CanAlterStatus
    @PutMapping("/{id}/open")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void open(@PathVariable Integer id) {
        service.open(id);
    }
    
    @Override
	@Permission.Restaurant.CanAlterStatus
    @PutMapping("/{id}/close")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void close(@PathVariable Integer id) {
        service.close(id);
    }
}
