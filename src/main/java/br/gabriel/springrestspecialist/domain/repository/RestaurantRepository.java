package br.gabriel.springrestspecialist.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gabriel.springrestspecialist.domain.model.Restaurant;
import br.gabriel.springrestspecialist.infrastructure.repository.CustomRestaurantRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>, CustomRestaurantRepository {
	List<Restaurant> findByNameContaining(String name);
	
	Optional<Restaurant> findFirstByNameContaining(String name);
	
	List<Restaurant> findByShippingFeeLessThan(BigDecimal fee);
}
