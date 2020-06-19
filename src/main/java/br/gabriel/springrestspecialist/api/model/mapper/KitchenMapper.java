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
    
    public KitchenResponse toModel(Kitchen kitchen) {
        return mapper.map(kitchen, KitchenResponse.class);
    }
    
    public List<KitchenResponse> toCollectionModel(List<Kitchen> kitchens) {
        return kitchens.stream().map(kitchen -> toModel(kitchen)).collect(Collectors.toList());
    }
    
    public Kitchen toDomainObject(KitchenRequest kitchenRequest) {
        return mapper.map(kitchenRequest, Kitchen.class);
    }
    
    public void copyToDomainObject(KitchenRequest kitchenRequest, Kitchen kitchen) {
        mapper.map(kitchenRequest, kitchen);
    }
}