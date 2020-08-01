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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
        return mapper.toCollectionModel(user.getGroups());
    }
    
    @Override
    @Permission.Write
    @PutMapping("{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@PathVariable Integer id, @PathVariable Integer groupId) {
        service.addToGroup(id, groupId);
    }
    
    @Override
    @Permission.Delete
    @DeleteMapping("{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id, @PathVariable Integer groupId) {
        service.removeFromGroup(id, groupId);
    }
}
