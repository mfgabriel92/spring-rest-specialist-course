package br.gabriel.springrestspecialist.api.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gabriel.springrestspecialist.api.model.response.RestaurantDetailResponse;
import br.gabriel.springrestspecialist.domain.model.Restaurant;

@Component
public class RestaurantDetailedMapper {
    @Autowired
    private ModelMapper mapper;
    
    public RestaurantDetailResponse toModel(Restaurant restaurant) {
        return mapper.map(restaurant, RestaurantDetailResponse.class);
    }
}