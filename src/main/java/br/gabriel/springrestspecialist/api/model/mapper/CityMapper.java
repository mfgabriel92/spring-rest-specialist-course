package br.gabriel.springrestspecialist.api.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gabriel.springrestspecialist.api.model.request.CityRequest;
import br.gabriel.springrestspecialist.api.model.response.CityResponse;
import br.gabriel.springrestspecialist.domain.model.City;
import br.gabriel.springrestspecialist.domain.model.State;

@Component
public class CityMapper {
    @Autowired
    private ModelMapper mapper;
    
    public CityResponse toModel(City city) {
        return mapper.map(city, CityResponse.class);
    }
    
    public List<CityResponse> toCollectionModel(List<City> cities) {
        return cities.stream().map(city -> toModel(city)).collect(Collectors.toList());
    }
    
    public City toDomainObject(CityRequest cityRequest) {
        return mapper.map(cityRequest, City.class);
    }
    
    public void copyToDomainObject(CityRequest cityRequest, City city) {
        city.setState(new State());
        mapper.map(cityRequest, city);
    }
}