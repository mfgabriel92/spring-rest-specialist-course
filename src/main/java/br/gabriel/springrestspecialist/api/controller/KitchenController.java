package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.domain.exception.ResourceInUseExeption;
import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;
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
	public ResponseEntity<Kitchen> findById(@PathVariable Integer id) {
		Kitchen kitchen = repository.findById(id);

		if (kitchen == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(kitchen);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen save(@RequestBody Kitchen kitchen) {
		return service.save(kitchen);
	}

	@PutMapping("{id}")
	public ResponseEntity<Kitchen> save(@PathVariable Integer id, @RequestBody Kitchen kitchen) {
		Kitchen current = repository.findById(id);

		if (current == null) {
			return ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(kitchen, current, "id");
		current = service.save(current);

		return ResponseEntity.ok(current);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		try {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (ResourceNotFoundExeption e) {
			return ResponseEntity.notFound().build();
		} catch (ResourceInUseExeption e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}