package br.gabriel.springrestspecialist.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.gabriel.springrestspecialist.domain.model.Restaurant;

@Repository
public class RestaurantRepositoryImpl implements CustomRestaurantRepository {
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurant> findBetweenShippingFees(BigDecimal minFee, BigDecimal maxFee) {
		String jpql = "FROM Restaurant WHERE shippingFee BETWEEN :minFee and :maxFee";
		
		return manager.createQuery(jpql, Restaurant.class)
			.setParameter("minFee", minFee)
			.setParameter("maxFee", maxFee)
			.getResultList();
	}
}
