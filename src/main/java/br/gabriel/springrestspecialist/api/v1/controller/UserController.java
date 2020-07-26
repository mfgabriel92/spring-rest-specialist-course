package br.gabriel.springrestspecialist.api.v1.controller;

import br.gabriel.springrestspecialist.api.mapper.UserMapper;
import br.gabriel.springrestspecialist.api.mapper.UserSummaryMapper;
import br.gabriel.springrestspecialist.api.v1.model.request.UserPasswordRequest;
import br.gabriel.springrestspecialist.api.v1.model.request.UserRequest;
import br.gabriel.springrestspecialist.api.v1.model.request.UserSummaryRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.UserResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.controller.UserDoc;
import br.gabriel.springrestspecialist.core.security.Permission;
import br.gabriel.springrestspecialist.domain.model.User;
import br.gabriel.springrestspecialist.domain.repository.UserRepository;
import br.gabriel.springrestspecialist.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
	@Permission.Read
    @GetMapping
	public List<UserResponse> findAll() {
		return mapper.toCollectionModel(repository.findAll());
	}
	
	@Override
	@Permission.User.CanReadSelf
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
	@Permission.User.CanWrite
    @PutMapping("{id}")
	public UserResponse save(@PathVariable Integer id, @RequestBody @Valid UserSummaryRequest userRequest) {
		User user = repository.findOrFail(id);
		summaryMapper.copyToDomainObject(userRequest, user);
		return mapper.toModel(service.save(user));
	}
	
	@Override
	@Permission.User.CanChangePassword
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
