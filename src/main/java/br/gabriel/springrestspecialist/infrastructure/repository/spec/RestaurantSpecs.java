package br.gabriel.springrestspecialist.infrastructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import br.gabriel.springrestspecialist.domain.model.Restaurant;

public class RestaurantSpecs {
	public static Specification<Restaurant> withShippingFeeBetween(BigDecimal minFee, BigDecimal maxFee) {
		return (root, query, builder) -> {
			return builder.between(root.get("shippingFee"), minFee, maxFee);
		};
	}
}
