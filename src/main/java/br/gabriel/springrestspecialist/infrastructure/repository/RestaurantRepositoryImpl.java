package br.gabriel.springrestspecialist.infrastructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.gabriel.springrestspecialist.domain.model.Restaurant;

@Repository
public class RestaurantRepositoryImpl implements CustomRestaurantRepository {
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurant> findBetweenShippingFees(BigDecimal minFee, BigDecimal maxFee) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("FROM Restaurant WHERE 0 = 0 ");
		
		HashMap<String, Object> params = new HashMap<>();
		
		if (minFee != null) {
			jpql.append("AND shippingFee >= :minFee ");
			params.put("minFee", minFee);
		}
		
		if (maxFee != null) {
			jpql.append("AND shippingFee <= :maxFee ");
			params.put("maxFee", maxFee);
		}
		
		TypedQuery<Restaurant> query = manager.createQuery(jpql.toString(), Restaurant.class);
		
		params.forEach((k, v) -> query.setParameter(k, v));
		
		return query.getResultList();
	}
}
