package br.gabriel.springrestspecialist.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gabriel.springrestspecialist.api.v1.model.response.RestaurantSummaryResponse;
import br.gabriel.springrestspecialist.domain.model.Restaurant;

@Component
public class RestaurantSummaryMapper {
    @Autowired
    private ModelMapper mapper;
    
    public RestaurantSummaryResponse toModel(Restaurant restaurant) {
        return mapper.map(restaurant, RestaurantSummaryResponse.class);
    }
    
    public List<RestaurantSummaryResponse> toCollectionModel(List<Restaurant> restaurants) {
        return restaurants.stream().map(restaurant -> toModel(restaurant)).collect(Collectors.toList());
    }
}