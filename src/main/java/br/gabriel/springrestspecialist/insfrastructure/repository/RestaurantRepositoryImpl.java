package br.gabriel.springrestspecialist.insfrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.model.Restaurant;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepository;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurant> findAll() {
		TypedQuery<Restaurant> query = manager.createQuery("FROM Restaurant", Restaurant.class);
		return query.getResultList();
	}
	
	@Override
	public List<Restaurant> findByName(String name) {
		TypedQuery<Restaurant> query = manager
			.createQuery("FROM Restaurant WHERE name LIKE :name", Restaurant.class)
			.setParameter("name", "%" + name + "%");
		
		return query.getResultList();
	}
	
	@Override
	@Transactional
	public Restaurant save(Restaurant restaurant) {
		return manager.merge(restaurant);
	}
	
	@Override
	public Restaurant findById(Integer id) {
		return manager.find(Restaurant.class, id);
	}
	
	@Override
	@Transactional
	public void delete(Restaurant restaurant) {
		restaurant = findById(restaurant.getId());
		manager.remove(restaurant);
	}
}
