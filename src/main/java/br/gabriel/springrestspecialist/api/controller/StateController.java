package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.api.controller.documentation.StateDoc;
import br.gabriel.springrestspecialist.api.model.mapper.StateMapper;
import br.gabriel.springrestspecialist.api.model.request.StateRequest;
import br.gabriel.springrestspecialist.api.model.response.StateResponse;
import br.gabriel.springrestspecialist.domain.model.State;
import br.gabriel.springrestspecialist.domain.repository.StateRepository;
import br.gabriel.springrestspecialist.domain.service.StateService;

@RestController
@RequestMapping("/states")
public class StateController implements StateDoc {
	@Autowired
	private StateRepository repository;
	
	@Autowired
	private StateService service;
	
	@Autowired
	private StateMapper mapper;

	@GetMapping
	public List<StateResponse> findAll() {
		return mapper.toCollectionModel(repository.findAll());
	}
	
	@GetMapping("{id}")
	public StateResponse findById(@PathVariable Integer id) {
		return mapper.toModel(repository.findOrFail(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public StateResponse save(@RequestBody @Valid StateRequest stateRequest) {
	    State state = mapper.toDomainObject(stateRequest);
		return mapper.toModel(service.save(state));
	}

	@PutMapping("{id}")
	public StateResponse save(@PathVariable Integer id, @RequestBody @Valid StateRequest stateRequest) {
		State state = repository.findOrFail(id);
		mapper.copyToDomainObject(stateRequest, state);
		return mapper.toModel(service.save(state));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}