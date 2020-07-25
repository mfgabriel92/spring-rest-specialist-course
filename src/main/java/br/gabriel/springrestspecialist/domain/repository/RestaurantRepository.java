package br.gabriel.springrestspecialist.domain.repository;

import br.gabriel.springrestspecialist.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends BaseJpaRepository<Restaurant, Integer>, JpaSpecificationExecutor<Restaurant> {
	@Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.cuisine LEFT JOIN FETCH r.paymentMethods")
	List<Restaurant> findAll();

	Boolean isOwnedBy(Integer restaurantId, Integer userId);
}
