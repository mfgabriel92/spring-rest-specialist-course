package br.gabriel.springrestspecialist.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gabriel.springrestspecialist.domain.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	List<Restaurant> findByNameContaining(String name);
	
	List<Restaurant> findByShippingFeeBetween(BigDecimal minFee, BigDecimal maxFee);
	
	Optional<Restaurant> findFirstByNameContaining(String name);
	
	@Query("FROM Restaurant WHERE shippingFee <= :fee")
	List<Restaurant> findByShippingFeeLessThan(BigDecimal fee);
}
