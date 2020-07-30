package br.gabriel.springrestspecialist.api.mapper;

import br.gabriel.springrestspecialist.api.v1.controller.CityController;
import br.gabriel.springrestspecialist.api.v1.model.request.CityRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.CityResponse;
import br.gabriel.springrestspecialist.domain.model.City;
import br.gabriel.springrestspecialist.domain.model.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CityMapper implements RepresentationModelAssembler<City, CityResponse> {
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public CityResponse toModel(City city) {
        CityResponse response = mapper.map(city, CityResponse.class);
    
        response.add(linkTo(methodOn(CityController.class).findById(city.getId())).withSelfRel());
    
        return response;
    }
    
    public City toDomainObject(CityRequest cityRequest) {
        return mapper.map(cityRequest, City.class);
    }
    
    public void copyToDomainObject(CityRequest cityRequest, City city) {
        city.setState(new State());
        mapper.map(cityRequest, city);
    }
}
