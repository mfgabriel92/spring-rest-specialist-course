package br.gabriel.springrestspecialist.api.v1.controller;

import br.gabriel.springrestspecialist.api.mapper.PermissionMapper;
import br.gabriel.springrestspecialist.api.v1.model.response.PermissionResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.controller.PermissionDoc;
import br.gabriel.springrestspecialist.domain.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissionController implements PermissionDoc {
    @Autowired
    private PermissionRepository repository;
    
    @Autowired
    private PermissionMapper mapper;
    
    @Override
    @GetMapping
    public CollectionModel<PermissionResponse> findAll() {
        return mapper.toCollectionModel(repository.findAll());
    }
}
