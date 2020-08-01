package br.gabriel.springrestspecialist.api.mapper;

import br.gabriel.springrestspecialist.api.v1.controller.RestaurantController;
import br.gabriel.springrestspecialist.api.v1.controller.RestaurantProductController;
import br.gabriel.springrestspecialist.api.v1.controller.RestaurantUserController;
import br.gabriel.springrestspecialist.api.v1.model.request.RestaurantRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.RestaurantResponse;
import br.gabriel.springrestspecialist.domain.model.City;
import br.gabriel.springrestspecialist.domain.model.Cuisine;
import br.gabriel.springrestspecialist.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RestaurantMapper implements RepresentationModelAssembler<Restaurant, RestaurantResponse> {
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public RestaurantResponse toModel(Restaurant restaurant) {
        RestaurantResponse response = mapper.map(restaurant, RestaurantResponse.class);
        
        response.add(linkTo(methodOn(RestaurantController.class).findById(restaurant.getId())).withSelfRel());
        response.add(linkTo(methodOn(RestaurantUserController.class).findAll(restaurant.getId())).withRel("users"));
        response.add(linkTo(methodOn(RestaurantProductController.class).findAll(restaurant.getId())).withRel("products"));
        
        if (restaurant.canActivate()) {
            response.add(linkTo(methodOn(RestaurantController.class).activate(restaurant.getId())).withRel("activate"));
        }
    
        if (restaurant.canDeactivate()) {
            response.add(linkTo(methodOn(RestaurantController.class).deactivate(restaurant.getId())).withRel("deactivate"));
        }
    
        if (restaurant.canOpen()) {
            response.add(linkTo(methodOn(RestaurantController.class).open(restaurant.getId())).withRel("open"));
        }
    
        if (restaurant.canClose()) {
            response.add(linkTo(methodOn(RestaurantController.class).close(restaurant.getId())).withRel("close"));
        }
        
        return response;
    }
    
    @Override
    public CollectionModel<RestaurantResponse> toCollectionModel(Iterable<? extends Restaurant> entities) {
        return RepresentationModelAssembler.super
            .toCollectionModel(entities)
            .add(linkTo(RestaurantController.class).withRel(IanaLinkRelations.COLLECTION));
    }
    
    public Restaurant toDomainObject(RestaurantRequest restaurantRequest) {
        return mapper.map(restaurantRequest, Restaurant.class);
    }
    
    public void copyToDomainObject(RestaurantRequest restaurantRequest, Restaurant restaurant) {
        restaurant.setCuisine(new Cuisine());
        
        if (restaurant.getAddress() != null) {
            restaurant.getAddress().setCity(new City());
        }
        
        mapper.map(restaurantRequest, restaurant);
    }
}
