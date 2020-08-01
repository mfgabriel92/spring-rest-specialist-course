package br.gabriel.springrestspecialist.api.v1.controller;

import br.gabriel.springrestspecialist.api.mapper.GroupMapper;
import br.gabriel.springrestspecialist.api.v1.model.response.GroupResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.controller.UserGroupDoc;
import br.gabriel.springrestspecialist.core.security.Permission;
import br.gabriel.springrestspecialist.domain.model.User;
import br.gabriel.springrestspecialist.domain.repository.UserRepository;
import br.gabriel.springrestspecialist.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/v1/users/{id}/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserGroupController implements UserGroupDoc {
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private UserService service;
    
    @Autowired
    private GroupMapper mapper;
    
    @Override
    @Permission.User.CanReadSelf
    @GetMapping
    public CollectionModel<GroupResponse> findAll(@PathVariable Integer id) {
        User user = repository.findOrFail(id);
        CollectionModel<GroupResponse> groupResponses = mapper.toCollectionModel(user.getGroups())
                                                              .add(linkTo(methodOn(UserGroupController.class).associate(id, null)).withRel("associate"));
    
        groupResponses.getContent().forEach(groupResponse ->
            groupResponse.add(linkTo(methodOn(GroupPermissionController.class).associate(id, user.getId())).withRel("disassociate")));
            
        return groupResponses;
    }
    
    @Override
    @Permission.Write
    @PutMapping("{groupId}")
    public ResponseEntity<Void> associate(@PathVariable Integer id, @PathVariable Integer groupId) {
        service.addToGroup(id, groupId);
        return ResponseEntity.noContent().build();
    }
    
    @Override
    @Permission.Delete
    @DeleteMapping("{groupId}")
    public ResponseEntity<Void> disassociate(@PathVariable Integer id, @PathVariable Integer groupId) {
        service.removeFromGroup(id, groupId);
        return ResponseEntity.noContent().build();
    }
}
