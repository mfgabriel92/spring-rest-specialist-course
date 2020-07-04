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

import br.gabriel.springrestspecialist.api.mapper.UserMapper;
import br.gabriel.springrestspecialist.api.mapper.UserSummaryMapper;
import br.gabriel.springrestspecialist.api.v1.model.request.UserPasswordRequest;
import br.gabriel.springrestspecialist.api.v1.model.request.UserRequest;
import br.gabriel.springrestspecialist.api.v1.model.request.UserSummaryRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.UserResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.controller.UserDoc;
import br.gabriel.springrestspecialist.domain.model.User;
import br.gabriel.springrestspecialist.domain.repository.UserRepository;
import br.gabriel.springrestspecialist.domain.service.UserService;

@RestController
@RequestMapping(path = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController implements UserDoc {
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
    private UserSummaryMapper summaryMapper;

	@Override
    @GetMapping
	public List<UserResponse> findAll() {
		return mapper.toCollectionModel(repository.findAll());
	}
	
	@Override
    @GetMapping("{id}")
	public UserResponse findById(@PathVariable Integer id) {
		return mapper.toModel(repository.findOrFail(id));
	}
	
	@Override
    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse save(@RequestBody @Valid UserRequest userRequest) {
	    User user = mapper.toDomainObject(userRequest);
		return mapper.toModel(service.save(user));
	}

	@Override
    @PutMapping("{id}")
	public UserResponse save(@PathVariable Integer id, @RequestBody @Valid UserSummaryRequest userRequest) {
		User user = repository.findOrFail(id);
		summaryMapper.copyToDomainObject(userRequest, user);
		return mapper.toModel(service.save(user));
	}
	
	@Override
    @PutMapping("{id}/password")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void password(@PathVariable Integer id, @RequestBody @Valid UserPasswordRequest passwordRequest) {
	    service.changePassword(id, passwordRequest.getCurrentPassword(), passwordRequest.getNewPassword());
    }

	@Override
    @DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}