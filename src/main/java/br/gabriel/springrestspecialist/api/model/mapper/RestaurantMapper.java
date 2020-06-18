package br.gabriel.springrestspecialist.api.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gabriel.springrestspecialist.api.model.request.RestaurantRequest;
import br.gabriel.springrestspecialist.api.model.response.RestaurantResponse;
import br.gabriel.springrestspecialist.domain.model.Restaurant;

@Component
public class RestaurantMapper {
    @Autowired
    private ModelMapper mapper;
    
    public RestaurantResponse toModel(Restaurant restaurant) {
        return mapper.map(restaurant, RestaurantResponse.class);
    }
    
    public List<RestaurantResponse> toCollectionModel(List<Restaurant> restaurants) {
        return restaurants.stream().map(restaurant -> toModel(restaurant)).collect(Collectors.toList());
    }
    
    public Restaurant toDomainObject(RestaurantRequest request) {
        return mapper.map(request, Restaurant.class);
    }
}