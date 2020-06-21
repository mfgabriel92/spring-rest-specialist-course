package br.gabriel.springrestspecialist.api.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gabriel.springrestspecialist.api.model.request.CuisineRequest;
import br.gabriel.springrestspecialist.api.model.response.CuisineResponse;
import br.gabriel.springrestspecialist.domain.model.Cuisine;

@Component
public class CuisineMapper {
    @Autowired
    private ModelMapper mapper;
    
    public CuisineResponse toModel(Cuisine cuisine) {
        return mapper.map(cuisine, CuisineResponse.class);
    }
    
    public List<CuisineResponse> toCollectionModel(List<Cuisine> cuisines) {
        return cuisines.stream().map(cuisine -> toModel(cuisine)).collect(Collectors.toList());
    }
    
    public Cuisine toDomainObject(CuisineRequest cuisineRequest) {
        return mapper.map(cuisineRequest, Cuisine.class);
    }
    
    public void copyToDomainObject(CuisineRequest cuisineRequest, Cuisine cuisine) {
        mapper.map(cuisineRequest, cuisine);
    }
}