package br.gabriel.springrestspecialist.api.mapper;

import br.gabriel.springrestspecialist.api.v1.controller.GroupController;
import br.gabriel.springrestspecialist.api.v1.controller.UserController;
import br.gabriel.springrestspecialist.api.v1.model.request.UserSummaryRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.UserResponse;
import br.gabriel.springrestspecialist.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserSummaryMapper implements RepresentationModelAssembler<User, UserResponse> {
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public UserResponse toModel(User user) {
        UserResponse response = mapper.map(user, UserResponse.class);
        
        response.add(linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel());
        response.add(linkTo(methodOn(GroupController.class).findById(user.getId())).withRel("groups"));
        
        return response;
    }
    
    @Override
    public CollectionModel<UserResponse> toCollectionModel(Iterable<? extends User> entities) {
        return RepresentationModelAssembler.super
            .toCollectionModel(entities)
            .add(linkTo(UserController.class).withRel(IanaLinkRelations.COLLECTION));
    }
    
    public void copyToDomainObject(UserSummaryRequest userRequest, User user) {
        mapper.map(userRequest, user);
    }
}
