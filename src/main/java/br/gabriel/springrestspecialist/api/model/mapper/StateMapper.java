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
    
    public State toDomainObject(StateRequest stateRequest) {
        return mapper.map(stateRequest, State.class);
    }
    
    public void copyToDomainObject(StateRequest stateRequest, State state) {
        mapper.map(stateRequest, state);
    }
}