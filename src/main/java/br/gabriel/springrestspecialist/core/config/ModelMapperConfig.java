package br.gabriel.springrestspecialist.core.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.gabriel.springrestspecialist.api.model.request.RestaurantRequest;
import br.gabriel.springrestspecialist.domain.model.Restaurant;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(RestaurantRequest.class, Restaurant.class)
            .addMappings(mapper -> mapper.skip(Restaurant::setId));

        return modelMapper;
    }
}
