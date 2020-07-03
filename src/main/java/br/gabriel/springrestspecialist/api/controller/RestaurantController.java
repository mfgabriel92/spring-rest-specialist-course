package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.api.controller.documentation.RestaurantDoc;
import br.gabriel.springrestspecialist.api.model.mapper.RestaurantMapper;
import br.gabriel.springrestspecialist.api.model.mapper.RestaurantSummaryMapper;
import br.gabriel.springrestspecialist.api.model.request.RestaurantRequest;
import br.gabriel.springrestspecialist.api.model.response.RestaurantResponse;
import br.gabriel.springrestspecialist.api.model.response.RestaurantSummaryResponse;
import br.gabriel.springrestspecialist.domain.model.Restaurant;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepository;
import br.gabriel.springrestspecialist.domain.service.RestaurantService;

@RestController
@RequestMapping(path = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController implements RestaurantDoc {
	@Autowired
	private RestaurantRepository repository;

	@Autowired
	private RestaurantService service;
	
	@Autowired
	private RestaurantMapper mapper;
	
	@Autowired
    private RestaurantSummaryMapper summaryMapper;

	@Override
    @GetMapping
	public Page<RestaurantSummaryResponse> findAll(Pageable pageable) {
	    Page<Restaurant> pagedRestaurants = repository.findAll(pageable);
	    List<RestaurantSummaryResponse> restaurants = summaryMapper.toCollectionModel(pagedRestaurants.getContent());
		return new PageImpl<>(restaurants, pageable, pagedRestaurants.getTotalElements());
	}

	@Override
    @GetMapping("{id}")
	public RestaurantResponse findById(@PathVariable Integer id) {
		return mapper.toModel(repository.findOrFail(id));
	}
	
	@Override
    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestaurantResponse save(@RequestBody @Valid RestaurantRequest restaurantRequest) {
	    Restaurant restaurant = mapper.toDomainObject(restaurantRequest);
		return mapper.toModel(service.save(restaurant));
	}
	
	@Override
    @PutMapping("/{id}")
	public RestaurantResponse save(@PathVariable Integer id, @RequestBody @Valid RestaurantRequest restaurantRequest) {
		Restaurant restaurant = repository.findOrFail(id);
		mapper.copyToDomainObject(restaurantRequest, restaurant);
		return mapper.toModel(service.save(restaurant));
	}
	
	@Override
    @PutMapping("/{id}/activate")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void activate(@PathVariable Integer id) {
	    service.activate(id);
	}
	
	@Override
    @PutMapping("/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@RequestBody List<Integer> ids) {
        service.activate(ids);
    }
	
	@Override
    @PutMapping("/{id}/deactivate")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@PathVariable Integer id) {
        service.deactivate(id);
    }
	
	@Override
    @PutMapping("/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@RequestBody List<Integer> ids) {
        service.deactivate(ids);
    }
	
	@Override
    @PutMapping("/{id}/open")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void open(@PathVariable Integer id) {
        service.open(id);
    }
    
    @Override
    @PutMapping("/{id}/close")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void close(@PathVariable Integer id) {
        service.close(id);
    }
}