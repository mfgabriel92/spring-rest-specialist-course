package br.gabriel.springrestspecialist.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.gabriel.springrestspecialist.domain.model.Restaurant;

@Repository
public class RestaurantRepositoryImpl implements CustomRestaurantRepository {
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurant> findBetweenShippingFees(BigDecimal minFee, BigDecimal maxFee) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
		Root<Restaurant> root = criteria.from(Restaurant.class);
		
		Predicate minFeePredicate = builder.greaterThanOrEqualTo(root.get("shippingFee"), minFee);
		Predicate maxFeePredicate = builder.lessThanOrEqualTo(root.get("shippingFee"), maxFee);
		
		criteria.where(minFeePredicate, maxFeePredicate);
		
		return manager.createQuery(criteria).getResultList();
	}
}
