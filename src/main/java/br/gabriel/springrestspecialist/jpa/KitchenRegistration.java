package br.gabriel.springrestspecialist.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import br.gabriel.springrestspecialist.domain.model.Kitchen;

@Component
public class KitchenRegistration {
	@PersistenceContext
	private EntityManager manager;
	
	public List<Kitchen> findAll() {
		TypedQuery<Kitchen> query = manager.createQuery("FROM Kitchen", Kitchen.class);
		return query.getResultList();
	}
}
