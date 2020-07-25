package br.gabriel.springrestspecialist.api.v1.controller;

import br.gabriel.springrestspecialist.api.mapper.StateMapper;
import br.gabriel.springrestspecialist.api.v1.model.request.StateRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.StateResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.controller.StateDoc;
import br.gabriel.springrestspecialist.core.security.Permission;
import br.gabriel.springrestspecialist.domain.model.State;
import br.gabriel.springrestspecialist.domain.repository.StateRepository;
import br.gabriel.springrestspecialist.domain.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/states", produces = MediaType.APPLICATION_JSON_VALUE)
public class StateController implements StateDoc {
	@Autowired
	private StateRepository repository;
	
	@Autowired
	private StateService service;
	
	@Autowired
	private StateMapper mapper;

	@Override
	@Permission.State.CanRead
    @GetMapping
	public List<StateResponse> findAll() {
		return mapper.toCollectionModel(repository.findAll());
	}
	
	@Override
	@Permission.State.CanRead
    @GetMapping("{id}")
	public StateResponse findById(@PathVariable Integer id) {
		return mapper.toModel(repository.findOrFail(id));
	}
	
	@Override
	@Permission.Write
    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public StateResponse save(@RequestBody @Valid StateRequest stateRequest) {
	    State state = mapper.toDomainObject(stateRequest);
		return mapper.toModel(service.save(state));
	}

	@Override
	@Permission.Write
    @PutMapping("{id}")
	public StateResponse save(@PathVariable Integer id, @RequestBody @Valid StateRequest stateRequest) {
		State state = repository.findOrFail(id);
		mapper.copyToDomainObject(stateRequest, state);
		return mapper.toModel(service.save(state));
	}

	@Override
	@Permission.Write
    @DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}
