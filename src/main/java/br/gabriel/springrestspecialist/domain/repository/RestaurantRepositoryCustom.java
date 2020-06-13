package br.gabriel.springrestspecialist.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import br.gabriel.springrestspecialist.domain.model.Restaurant;

public interface RestaurantRepositoryCustom {
	List<Restaurant> findWithShippingFeesBetween(BigDecimal minFee, BigDecimal maxFee);
}
