package br.gabriel.springrestspecialist.insfrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.model.Kitchen;
import br.gabriel.springrestspecialist.domain.repository.KitchenRepository;

@Component
public class KitchenRepositoryImpl implements KitchenRepository {
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Kitchen> findAll() {
		TypedQuery<Kitchen> query = manager.createQuery("FROM Kitchen", Kitchen.class);
		return query.getResultList();
	}
	
	@Override
	@Transactional
	public Kitchen save(Kitchen kitchen) {
		return manager.merge(kitchen);
	}
	
	@Override
	public Kitchen findById(Integer id) {
		return manager.find(Kitchen.class, id);
	}
	
	@Override
	@Transactional
	public void deleteById(Integer id) {
		Kitchen kitchen = findById(id);
		
		if (kitchen == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(kitchen);
	}
}
