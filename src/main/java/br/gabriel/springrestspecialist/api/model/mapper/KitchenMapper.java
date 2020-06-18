package br.gabriel.springrestspecialist.api.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gabriel.springrestspecialist.api.model.request.KitchenRequest;
import br.gabriel.springrestspecialist.api.model.response.KitchenResponse;
import br.gabriel.springrestspecialist.domain.model.Kitchen;

@Component
public class KitchenMapper {
    @Autowired
    private ModelMapper mapper;
    
    public KitchenResponse toModel(Kitchen restaurant) {
        return mapper.map(restaurant, KitchenResponse.class);
    }
    
    public List<KitchenResponse> toCollectionModel(List<Kitchen> restaurants) {
        return restaurants.stream().map(restaurant -> toModel(restaurant)).collect(Collectors.toList());
    }
    
    public Kitchen toDomainObject(KitchenRequest request) {
        return mapper.map(request, Kitchen.class);
    }
}