package br.gabriel.springrestspecialist.api.mapper;

import br.gabriel.springrestspecialist.api.v1.controller.CuisineController;
import br.gabriel.springrestspecialist.api.v1.model.request.CuisineRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.CuisineResponse;
import br.gabriel.springrestspecialist.domain.model.Cuisine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CuisineMapper implements RepresentationModelAssembler<Cuisine, CuisineResponse> {
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public CuisineResponse toModel(Cuisine cuisine) {
        CuisineResponse response = mapper.map(cuisine, CuisineResponse.class);
        
        response.add(linkTo(methodOn(CuisineController.class).findById(cuisine.getId())).withSelfRel());
        
        return response;
    }
    
    @Override
    public CollectionModel<CuisineResponse> toCollectionModel(Iterable<? extends Cuisine> entities) {
        return RepresentationModelAssembler.super
            .toCollectionModel(entities)
            .add(linkTo(CuisineController.class).withRel(IanaLinkRelations.COLLECTION));
    }
    
    public Cuisine toDomainObject(CuisineRequest cuisineRequest) {
        return mapper.map(cuisineRequest, Cuisine.class);
    }
    
    public void copyToDomainObject(CuisineRequest cuisineRequest, Cuisine cuisine) {
        mapper.map(cuisineRequest, cuisine);
    }
}
