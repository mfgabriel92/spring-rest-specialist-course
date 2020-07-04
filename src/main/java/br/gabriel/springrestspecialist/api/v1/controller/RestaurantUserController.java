package br.gabriel.springrestspecialist.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.api.mapper.UserMapper;
import br.gabriel.springrestspecialist.api.v1.model.response.UserResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.controller.RestaurantUserDoc;
import br.gabriel.springrestspecialist.domain.model.Restaurant;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepository;
import br.gabriel.springrestspecialist.domain.service.RestaurantService;

@RestController
@RequestMapping(path = "/v1/restaurants/{id}/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantUserController implements RestaurantUserDoc {
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Autowired
    private RestaurantService restaurantService;
    
    @Autowired
    private UserMapper mapper;
    
    @Override
    @GetMapping
    public List<UserResponse> findAll(@PathVariable Integer id) {
        Restaurant restaurant = restaurantRepository.findOrFail(id);
        return mapper.toCollectionModel(restaurant.getUsers());
    }
    
    @Override
    @PutMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addRestaurant(@PathVariable Integer id, @PathVariable Integer userId) {
        restaurantService.addUser(id, userId);
    }
    
    @Override
    @DeleteMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRestaurant(@PathVariable Integer id, @PathVariable Integer userId) {
        restaurantService.removeUser(id, userId);
    }
}