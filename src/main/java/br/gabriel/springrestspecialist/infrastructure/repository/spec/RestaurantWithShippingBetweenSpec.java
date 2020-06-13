package br.gabriel.springrestspecialist.infrastructure.repository.spec;

import java.math.BigDecimal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.gabriel.springrestspecialist.domain.model.Restaurant;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestaurantWithShippingBetweenSpec implements Specification<Restaurant> {
	private static final long serialVersionUID = 1L;
	
	private BigDecimal minFee;
	
	private BigDecimal maxFee;

	@Override
	public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return builder.between(root.get("shippingFee"), minFee, maxFee);
	}
}
