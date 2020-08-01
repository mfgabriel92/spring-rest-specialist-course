package br.gabriel.springrestspecialist.api.v1.controller;

import br.gabriel.springrestspecialist.api.mapper.PermissionMapper;
import br.gabriel.springrestspecialist.api.v1.model.response.PermissionResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.controller.GroupPermissionDoc;
import br.gabriel.springrestspecialist.core.security.Permission;
import br.gabriel.springrestspecialist.domain.model.Group;
import br.gabriel.springrestspecialist.domain.repository.GroupRepository;
import br.gabriel.springrestspecialist.domain.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/v1/groups/{id}/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupPermissionController implements GroupPermissionDoc {
    @Autowired
    private GroupRepository repository;
    
    @Autowired
    private GroupService service;
    
    @Autowired
    private PermissionMapper mapper;
    
    @Override
    @Permission.Read
    @GetMapping
    public CollectionModel<PermissionResponse> findAll(@PathVariable Integer id) {
        Group group = repository.findOrFail(id);
        CollectionModel<PermissionResponse> permissionResponses = mapper.toCollectionModel(group.getPermissions())
                                                                        .add(linkTo(methodOn(GroupPermissionController.class).associate(id, null)).withRel("associate"));
        
        permissionResponses.getContent().forEach(permissionResponse ->
            permissionResponse.add(linkTo(methodOn(GroupPermissionController.class).associate(id, group.getId())).withRel("disassociate")));
        
        return permissionResponses;
    }
    
    @Override
    @Permission.Write
    @PutMapping("{permissionId}")
    public ResponseEntity<Void> associate(@PathVariable Integer id, @PathVariable Integer permissionId) {
        service.addPermission(id, permissionId);
        return ResponseEntity.noContent().build();
    }
    
    @Override
    @Permission.Delete
    @DeleteMapping("{permissionId}")
    public ResponseEntity<Void> disassociate(@PathVariable Integer id, @PathVariable Integer permissionId) {
        service.removePermission(id, permissionId);
        return ResponseEntity.noContent().build();
    }
}
