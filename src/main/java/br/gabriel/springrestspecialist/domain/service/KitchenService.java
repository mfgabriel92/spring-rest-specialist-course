package br.gabriel.springrestspecialist.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gabriel.springrestspecialist.domain.exception.ResourceInUseExeption;
import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;
import br.gabriel.springrestspecialist.domain.model.Kitchen;
import br.gabriel.springrestspecialist.domain.repository.KitchenRepository;

@Service
public class KitchenService {
	@Autowired
	private KitchenRepository repository;

	public Kitchen save(Kitchen kitchen) {
		return repository.save(kitchen);
	}

	public void deleteById(Integer id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundExeption(String.format("The kitchen ID %d does not exist", id));
		} catch (DataIntegrityViolationException e) {
			throw new ResourceInUseExeption(String.format("The kitchen ID %d is being used by another resource", id));
		}
	}
}