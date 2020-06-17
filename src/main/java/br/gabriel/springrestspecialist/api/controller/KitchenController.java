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

import br.gabriel.springrestspecialist.domain.model.Kitchen;
import br.gabriel.springrestspecialist.domain.repository.KitchenRepository;
import br.gabriel.springrestspecialist.domain.service.KitchenService;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {
	@Autowired
	private KitchenRepository repository;

	@Autowired
	private KitchenService service;

	@GetMapping
	public List<Kitchen> findAll() {
		return repository.findAll();
	}

	@GetMapping("{id}")
	public Kitchen findById(@PathVariable Integer id) {
		return repository.findOrFail(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen save(@RequestBody @Valid Kitchen kitchen) {
		return service.save(kitchen);
	}

	@PutMapping("{id}")
	public Kitchen save(@PathVariable Integer id, @RequestBody @Valid Kitchen kitchen) {
		Kitchen current = repository.findOrFail(id);
		BeanUtils.copyProperties(kitchen, current, "id");
		
		return service.save(current);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}