package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.api.controller.documentation.UserGroupDoc;
import br.gabriel.springrestspecialist.api.model.mapper.GroupMapper;
import br.gabriel.springrestspecialist.api.model.response.GroupResponse;
import br.gabriel.springrestspecialist.domain.model.User;
import br.gabriel.springrestspecialist.domain.repository.UserRepository;
import br.gabriel.springrestspecialist.domain.service.UserService;

@RestController
@RequestMapping("/users/{id}/groups")
public class UserGroupController implements UserGroupDoc {
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private UserService service;
    
    @Autowired
    private GroupMapper mapper;
    
    @Override
    @GetMapping
    public List<GroupResponse> findAll(@PathVariable Integer id) {
        User user = repository.findOrFail(id);
        return mapper.toCollectionModel(user.getGroups());
    }
    
    @Override
    @PutMapping("{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@PathVariable Integer id, @PathVariable Integer groupId) {
        service.addToGroup(id, groupId);
    }
    
    @Override
    @DeleteMapping("{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id, @PathVariable Integer groupId) {
        service.removeFromGroup(id, groupId);
    }
}
