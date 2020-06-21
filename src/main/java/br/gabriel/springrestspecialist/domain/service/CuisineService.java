package br.gabriel.springrestspecialist.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.model.Cuisine;
import br.gabriel.springrestspecialist.domain.repository.CuisineRepository;

@Service
public class CuisineService {
	@Autowired
	private CuisineRepository repository;

	@Transactional
	public Cuisine save(Cuisine cuisine) {
		return repository.save(cuisine);
	}

	@Transactional
	public void deleteById(Integer id) {
	    repository.deleteOrFail(id);
	}
}