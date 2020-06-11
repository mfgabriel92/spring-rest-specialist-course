package br.gabriel.springrestspecialist.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.model.Kitchen;

@Component
public class KitchenRegistration {
	@PersistenceContext
	private EntityManager manager;
	
	public List<Kitchen> findAll() {
		TypedQuery<Kitchen> query = manager.createQuery("FROM Kitchen", Kitchen.class);
		return query.getResultList();
	}
	
	@Transactional
	public Kitchen save(Kitchen kitchen) {
		return manager.merge(kitchen);
	}
	
	public Kitchen findById(Integer id) {
		return manager.find(Kitchen.class, id);
	}
	
	@Transactional
	public void remove(Kitchen kitchen) {
		kitchen = findById(kitchen.getId());
		manager.remove(kitchen);
	}
}
