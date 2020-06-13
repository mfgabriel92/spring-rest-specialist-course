package br.gabriel.springrestspecialist.insfrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.model.State;
import br.gabriel.springrestspecialist.domain.repository.StateRepository;

@Component
public class StateRepositoryImpl implements StateRepository {
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<State> findAll() {
		TypedQuery<State> query = manager.createQuery("FROM State", State.class);
		return query.getResultList();
	}
	
	@Override
	@Transactional
	public State save(State State) {
		return manager.merge(State);
	}
	
	@Override
	public State findById(Integer id) {
		return manager.find(State.class, id);
	}
	
	@Override
	@Transactional
	public void deleteById(Integer id) {
		State state = findById(id);
		
		if (state == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(state);
	}
}
