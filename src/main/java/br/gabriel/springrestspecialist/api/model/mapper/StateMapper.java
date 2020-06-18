package br.gabriel.springrestspecialist.api.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gabriel.springrestspecialist.api.model.request.StateRequest;
import br.gabriel.springrestspecialist.api.model.response.StateResponse;
import br.gabriel.springrestspecialist.domain.model.State;

@Component
public class StateMapper {
    @Autowired
    private ModelMapper mapper;
    
    public StateResponse toModel(State state) {
        return mapper.map(state, StateResponse.class);
    }
    
    public List<StateResponse> toCollectionModel(List<State> states) {
        return states.stream().map(state -> toModel(state)).collect(Collectors.toList());
    }
    
    public State toDomainObject(StateRequest request) {
        return mapper.map(request, State.class);
    }
    
    public void copyToDomainObject(StateRequest request, State state) {
        mapper.map(request, state);
    }
}