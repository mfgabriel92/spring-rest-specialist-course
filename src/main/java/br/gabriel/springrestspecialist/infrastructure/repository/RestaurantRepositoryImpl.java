package br.gabriel.springrestspecialist.infrastructure.repository;

import static br.gabriel.springrestspecialist.infrastructure.repository.spec.RestaurantSpecs.withShippingFeeBetween;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import br.gabriel.springrestspecialist.domain.model.Restaurant;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepository;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepositoryCustom;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryCustom {
	@Lazy
	@Autowired
	private RestaurantRepository repository;
	
	@Override
	public List<Restaurant> findWithShippingFeesBetween(BigDecimal minFee, BigDecimal maxFee) {
		return repository.findAll(withShippingFeeBetween(minFee, maxFee));
	}
}
