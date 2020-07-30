package br.gabriel.springrestspecialist.api.mapper;

import br.gabriel.springrestspecialist.api.v1.controller.StateController;
import br.gabriel.springrestspecialist.api.v1.model.request.StateRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.StateResponse;
import br.gabriel.springrestspecialist.domain.model.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StateMapper implements RepresentationModelAssembler<State, StateResponse> {
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public StateResponse toModel(State state) {
        StateResponse response = mapper.map(state, StateResponse.class);
        
        response.add(linkTo(methodOn(StateController.class).findById(state.getId())).withSelfRel());
        
        return response;
    }
    
    @Override
    public CollectionModel<StateResponse> toCollectionModel(Iterable<? extends State> entities) {
        return RepresentationModelAssembler.super
            .toCollectionModel(entities)
            .add(linkTo(StateController.class).withRel(IanaLinkRelations.COLLECTION));
    }
    
    public State toDomainObject(StateRequest stateRequest) {
        return mapper.map(stateRequest, State.class);
    }
    
    public void copyToDomainObject(StateRequest stateRequest, State state) {
        mapper.map(stateRequest, state);
    }
}
