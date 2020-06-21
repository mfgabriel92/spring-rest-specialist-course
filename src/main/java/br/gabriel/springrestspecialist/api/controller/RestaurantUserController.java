package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.api.model.mapper.UserMapper;
import br.gabriel.springrestspecialist.api.model.response.UserResponse;
import br.gabriel.springrestspecialist.domain.model.Restaurant;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepository;
import br.gabriel.springrestspecialist.domain.service.RestaurantService;

@RestController
@RequestMapping("/restaurants/{id}/users")
public class RestaurantUserController {
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Autowired
    private RestaurantService restaurantService;
    
    @Autowired
    private UserMapper mapper;
    
    @GetMapping
    public List<UserResponse> findAll(@PathVariable Integer id) {
        Restaurant restaurant = restaurantRepository.findOrFail(id);
        return mapper.toCollectionModel(restaurant.getUsers());
    }
    
    @PutMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addRestaurant(@PathVariable Integer id, @PathVariable Integer userId) {
        restaurantService.addUser(id, userId);
    }
    
    @DeleteMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRestaurant(@PathVariable Integer id, @PathVariable Integer userId) {
        restaurantService.removeUser(id, userId);
    }
}