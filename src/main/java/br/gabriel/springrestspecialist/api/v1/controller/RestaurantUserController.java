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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        
        CollectionModel<UserResponse> userResponses = mapper.toCollectionModel(restaurant.getUsers()).removeLinks()
                     .add(linkTo(methodOn(RestaurantUserController.class).findAll(id)).withSelfRel())
                     .add(linkTo(methodOn(RestaurantUserController.class).assignToRestaurant(id, null)).withRel("assign-user"));
        
        userResponses.getContent()
                     .forEach(userResponse -> userResponse
                         .add(linkTo(methodOn(RestaurantUserController.class)
                         .unassignFromRestaurant(id, userResponse.getId())).withRel("unassign-user")));
        
        return userResponses;
    }
    
    @Override
    @Permission.Write
    @PutMapping("{userId}")
    public ResponseEntity<Void> assignToRestaurant(@PathVariable Integer id, @PathVariable Integer userId) {
        restaurantService.addUser(id, userId);
        return ResponseEntity.noContent().build();
    }
    
    @Override
    @Permission.Write
    @DeleteMapping("{userId}")
    public ResponseEntity<Void> unassignFromRestaurant(@PathVariable Integer id, @PathVariable Integer userId) {
        restaurantService.removeUser(id, userId);
        return ResponseEntity.noContent().build();
    }
}
