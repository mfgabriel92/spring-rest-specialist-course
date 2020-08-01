package br.gabriel.springrestspecialist.api.mapper;

import br.gabriel.springrestspecialist.api.v1.controller.RestaurantController;
import br.gabriel.springrestspecialist.api.v1.model.response.RestaurantSummaryResponse;
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
public class RestaurantSummaryMapper implements RepresentationModelAssembler<Restaurant, RestaurantSummaryResponse> {
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public RestaurantSummaryResponse toModel(Restaurant restaurant) {
        RestaurantSummaryResponse response = mapper.map(restaurant, RestaurantSummaryResponse.class);
    
        response.add(linkTo(methodOn(RestaurantController.class).findById(restaurant.getId())).withSelfRel());
    
        return response;
    }
    
    @Override
    public CollectionModel<RestaurantSummaryResponse> toCollectionModel(Iterable<? extends Restaurant> entities) {
        return RepresentationModelAssembler.super
            .toCollectionModel(entities)
            .add(linkTo(RestaurantController.class).withRel(IanaLinkRelations.COLLECTION));
    }
}
