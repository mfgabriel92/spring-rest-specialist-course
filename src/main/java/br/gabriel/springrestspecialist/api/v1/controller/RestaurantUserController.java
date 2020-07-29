package br.gabriel.springrestspecialist.api.v1.controller;

import br.gabriel.springrestspecialist.api.mapper.UserMapper;
import br.gabriel.springrestspecialist.api.v1.model.response.UserResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.controller.RestaurantUserDoc;
import br.gabriel.springrestspecialist.core.security.Permission;
import br.gabriel.springrestspecialist.domain.model.Restaurant;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepository;
import br.gabriel.springrestspecialist.domain.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    @Permission.Read
    @GetMapping
    public CollectionModel<UserResponse> findAll(@PathVariable Integer id) {
        Restaurant restaurant = restaurantRepository.findOrFail(id);
        return mapper.toCollectionModel(restaurant.getUsers());
    }
    
    @Override
    @Permission.Write
    @PutMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void assignToRestaurant(@PathVariable Integer id, @PathVariable Integer userId) {
        restaurantService.addUser(id, userId);
    }
    
    @Override
    @Permission.Write
    @DeleteMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unassignFromRestaurant(@PathVariable Integer id, @PathVariable Integer userId) {
        restaurantService.removeUser(id, userId);
    }
}
