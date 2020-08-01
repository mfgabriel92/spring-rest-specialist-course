package br.gabriel.springrestspecialist.api.mapper;

import br.gabriel.springrestspecialist.api.v1.controller.GroupController;
import br.gabriel.springrestspecialist.api.v1.model.request.GroupRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.GroupResponse;
import br.gabriel.springrestspecialist.domain.model.Group;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GroupMapper implements RepresentationModelAssembler<Group, GroupResponse> {
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public GroupResponse toModel(Group group) {
        GroupResponse response = mapper.map(group, GroupResponse.class);
        
        response.add(linkTo(methodOn(GroupController.class).findById(group.getId())).withSelfRel());
        
        return response;
    }
    
    @Override
    public CollectionModel<GroupResponse> toCollectionModel(Iterable<? extends Group> entities) {
        return RepresentationModelAssembler.super
            .toCollectionModel(entities)
            .add(linkTo(GroupController.class).withRel(IanaLinkRelations.COLLECTION));
    }
    
    public Group toDomainObject(GroupRequest groupRequest) {
        return mapper.map(groupRequest, Group.class);
    }
    
    public void copyToDomainObject(GroupRequest groupRequest, Group group) {
        mapper.map(groupRequest, group);
    }
}
