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

import br.gabriel.springrestspecialist.api.controller.documentation.GroupPermissionDoc;
import br.gabriel.springrestspecialist.api.model.mapper.PermissionMapper;
import br.gabriel.springrestspecialist.api.model.response.PermissionResponse;
import br.gabriel.springrestspecialist.domain.model.Group;
import br.gabriel.springrestspecialist.domain.repository.GroupRepository;
import br.gabriel.springrestspecialist.domain.service.GroupService;

@RestController
@RequestMapping("/groups/{id}/permissions")
public class GroupPermissionController implements GroupPermissionDoc {
    @Autowired
    private GroupRepository repository;
    
    @Autowired
    private GroupService service;
    
    @Autowired
    private PermissionMapper mapper;
    
    @Override
    @GetMapping
    public List<PermissionResponse> findAll(@PathVariable Integer id) {
        Group group = repository.findOrFail(id);
        return mapper.toCollectionModel(group.getPermissions());
    }
    
    @Override
    @PutMapping("{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@PathVariable Integer id, @PathVariable Integer permissionId) {
        service.addPermission(id, permissionId);
    }
    
    @Override
    @DeleteMapping("{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id, @PathVariable Integer permissionId) {
        service.removePermission(id, permissionId);
    }
}
