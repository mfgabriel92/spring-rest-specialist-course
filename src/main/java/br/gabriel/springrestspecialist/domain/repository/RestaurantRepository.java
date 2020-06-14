package br.gabriel.springrestspecialist.domain.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.gabriel.springrestspecialist.domain.model.Restaurant;

public interface RestaurantRepository extends BaseJpaRepository<Restaurant, Integer>, JpaSpecificationExecutor<Restaurant> {
}
