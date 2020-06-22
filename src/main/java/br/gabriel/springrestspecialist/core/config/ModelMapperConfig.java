package br.gabriel.springrestspecialist.core.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.gabriel.springrestspecialist.api.model.request.CityRequest;
import br.gabriel.springrestspecialist.api.model.request.OrderItemRequest;
import br.gabriel.springrestspecialist.api.model.request.OrderRequest;
import br.gabriel.springrestspecialist.api.model.request.RestaurantRequest;
import br.gabriel.springrestspecialist.domain.model.City;
import br.gabriel.springrestspecialist.domain.model.Order;
import br.gabriel.springrestspecialist.domain.model.OrderItem;
import br.gabriel.springrestspecialist.domain.model.Restaurant;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(RestaurantRequest.class, Restaurant.class)
            .addMappings(mapper -> mapper.skip(Restaurant::setId));
        
        modelMapper.createTypeMap(CityRequest.class, City.class)
            .addMappings(mapper -> mapper.skip(City::setId));
        
        modelMapper.createTypeMap(OrderRequest.class, Order.class)
            .addMappings(mapper -> mapper.skip(Order::setId));
        
        modelMapper.createTypeMap(OrderItemRequest.class, OrderItem.class)
            .addMappings(mapper -> mapper.skip(OrderItem::setId));

        return modelMapper;
    }
}
