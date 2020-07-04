package br.gabriel.springrestspecialist.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.api.mapper.GroupMapper;
import br.gabriel.springrestspecialist.api.v1.model.request.GroupRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.GroupResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.controller.GroupDoc;
import br.gabriel.springrestspecialist.domain.model.Group;
import br.gabriel.springrestspecialist.domain.repository.GroupRepository;
import br.gabriel.springrestspecialist.domain.service.GroupService;

@RestController
@RequestMapping(path = "/v1/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController implements GroupDoc {
	@Autowired
	private GroupRepository repository;
	
	@Autowired
	private GroupService service;
	
	@Autowired
	private GroupMapper mapper;

	@Override
    @GetMapping
	public List<GroupResponse> findAll() {
		return mapper.toCollectionModel(repository.findAll());
	}
	
	@Override
    @GetMapping("{id}")
	public GroupResponse findById(@PathVariable Integer id) {
		return mapper.toModel(repository.findOrFail(id));
	}
	
	@Override
    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GroupResponse save(@RequestBody @Valid GroupRequest groupRequest) {
	    Group group = mapper.toDomainObject(groupRequest);
		return mapper.toModel(service.save(group));
	}

	@Override
    @PutMapping("{id}")
	public GroupResponse save(@PathVariable Integer id, @RequestBody @Valid GroupRequest groupRequest) {
		Group group = repository.findOrFail(id);
		mapper.copyToDomainObject(groupRequest, group);
		return mapper.toModel(service.save(group));
	}

	@Override
    @DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}