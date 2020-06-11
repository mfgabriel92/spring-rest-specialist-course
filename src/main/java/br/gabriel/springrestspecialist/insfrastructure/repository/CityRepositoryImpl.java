package br.gabriel.springrestspecialist.insfrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.model.City;
import br.gabriel.springrestspecialist.domain.repository.CityRepository;

@Component
public class CityRepositoryImpl implements CityRepository {
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<City> findAll() {
		TypedQuery<City> query = manager.createQuery("FROM City", City.class);
		return query.getResultList();
	}
	
	@Override
	@Transactional
	public City save(City City) {
		return manager.merge(City);
	}
	
	@Override
	public City findById(Integer id) {
		return manager.find(City.class, id);
	}
	
	@Override
	@Transactional
	public void delete(City City) {
		City = findById(City.getId());
		manager.remove(City);
	}
}
