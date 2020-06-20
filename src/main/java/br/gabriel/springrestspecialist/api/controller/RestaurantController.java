package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import br.gabriel.springrestspecialist.api.model.mapper.RestaurantMapper;
import br.gabriel.springrestspecialist.api.model.mapper.RestaurantSummaryMapper;
import br.gabriel.springrestspecialist.api.model.request.RestaurantRequest;
import br.gabriel.springrestspecialist.api.model.response.RestaurantResponse;
import br.gabriel.springrestspecialist.api.model.response.RestaurantSummaryResponse;
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
	
	@Autowired
	private RestaurantMapper mapper;
	
	@Autowired
    private RestaurantSummaryMapper summaryMapper;

	@GetMapping
	public List<RestaurantSummaryResponse> findAll() {
		return summaryMapper.toCollectionModel(repository.findAll());
	}

	@GetMapping("{id}")
	public RestaurantResponse findById(@PathVariable Integer id) {
		return mapper.toModel(repository.findOrFail(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestaurantResponse save(@RequestBody @Valid RestaurantRequest restaurantRequest) {
	    Restaurant restaurant = mapper.toDomainObject(restaurantRequest);
		return mapper.toModel(service.save(restaurant));
	}
	
	@PutMapping("/{id}")
	public RestaurantResponse save(@PathVariable Integer id, @RequestBody @Valid RestaurantRequest restaurantRequest) {
		Restaurant restaurant = repository.findOrFail(id);
		mapper.copyToDomainObject(restaurantRequest, restaurant);
		return mapper.toModel(service.save(restaurant));
	}
	
	@PutMapping("/{id}/activate")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void activate(@PathVariable Integer id) {
	    service.activate(id);
	}
	
	@PutMapping("/{id}/deactivate")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@PathVariable Integer id) {
        service.deactivate(id);
    }
	
	@PutMapping("/{id}/open")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void open(@PathVariable Integer id) {
        service.open(id);
    }
    
    @PutMapping("/{id}/close")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void close(@PathVariable Integer id) {
        service.close(id);
    }
}