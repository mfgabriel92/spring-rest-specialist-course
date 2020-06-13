package br.gabriel.springrestspecialist.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import br.gabriel.springrestspecialist.domain.model.Restaurant;

public interface CustomRestaurantRepository {
	List<Restaurant> findBetweenShippingFees(BigDecimal minFee, BigDecimal maxFee);
}