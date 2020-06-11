package br.gabriel.springrestspecialist.domain.repository;

import java.util.List;

import br.gabriel.springrestspecialist.domain.model.State;

public interface StateRepository {
	List<State> findAll();
	
	State findById(Integer id);
	
	State save(State paymenMethod);
	
	void delete(State paymenMethod);
}
