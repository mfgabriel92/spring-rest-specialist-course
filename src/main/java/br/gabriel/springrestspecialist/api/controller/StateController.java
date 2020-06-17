package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import br.gabriel.springrestspecialist.domain.model.State;
import br.gabriel.springrestspecialist.domain.repository.StateRepository;
import br.gabriel.springrestspecialist.domain.service.StateService;

@RestController
@RequestMapping("/states")
public class StateController {
	@Autowired
	private StateRepository repository;
	
	@Autowired
	private StateService service;

	@GetMapping
	public List<State> findAll() {
		return repository.findAll();
	}
	
	@GetMapping("{id}")
	public State findById(@PathVariable Integer id) {
		return repository.findOrFail(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public State save(@RequestBody @Valid State state) {
		return service.save(state);
	}

	@PutMapping("{id}")
	public State save(@PathVariable Integer id, @RequestBody @Valid State state) {
		State current = repository.findOrFail(id);
		BeanUtils.copyProperties(state, current, "id");
		
		return service.save(current);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}