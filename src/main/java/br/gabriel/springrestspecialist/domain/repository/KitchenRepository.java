package br.gabriel.springrestspecialist.domain.repository;

import java.util.List;

import br.gabriel.springrestspecialist.domain.model.Kitchen;

public interface KitchenRepository {
	List<Kitchen> findAll();
	
	Kitchen findById(Integer id);
	
	Kitchen save(Kitchen kitchen);
	
	void delete(Kitchen kitchen);
}
