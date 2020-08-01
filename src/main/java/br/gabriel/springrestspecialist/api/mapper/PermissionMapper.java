package br.gabriel.springrestspecialist.api.mapper;

import br.gabriel.springrestspecialist.api.v1.controller.PermissionController;
import br.gabriel.springrestspecialist.api.v1.model.response.PermissionResponse;
import br.gabriel.springrestspecialist.domain.model.Permission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class PermissionMapper implements RepresentationModelAssembler<Permission, PermissionResponse> {
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public PermissionResponse toModel(Permission permission) {
        return mapper.map(permission, PermissionResponse.class);
    }
    
    @Override
    public CollectionModel<PermissionResponse> toCollectionModel(Iterable<? extends Permission> entities) {
        return RepresentationModelAssembler.super
            .toCollectionModel(entities)
            .add(linkTo(PermissionController.class).withRel(IanaLinkRelations.COLLECTION));
    }
}
