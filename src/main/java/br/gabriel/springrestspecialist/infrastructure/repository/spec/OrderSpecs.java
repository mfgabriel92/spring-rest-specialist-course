package br.gabriel.springrestspecialist.infrastructure.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.gabriel.springrestspecialist.domain.filter.OrderFilter;
import br.gabriel.springrestspecialist.domain.model.Order;

public class OrderSpecs {
	public static Specification<Order> filteringBy(OrderFilter filter) {
		return (root, query, builder) -> {
		    if (query.getResultType().equals(Order.class)) {
		        root.fetch("restaurant").fetch("cuisine");
	            root.fetch("user");
		    }
		    
			List<Predicate> predicates = new ArrayList<>();
			
			if (filter.getUserId() != null) {
			    predicates.add(builder.equal(root.get("user"), filter.getUserId()));
			} else if (filter.getRestaurantId() != null) {
			    predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
			} else if (filter.getInitialDate() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("createdAt"), filter.getInitialDate()));
            } else if (filter.getEndingDate() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("createdAt"), filter.getEndingDate()));
            }
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
