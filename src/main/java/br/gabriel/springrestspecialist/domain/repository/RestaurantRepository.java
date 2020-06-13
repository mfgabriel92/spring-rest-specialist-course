package br.gabriel.springrestspecialist.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gabriel.springrestspecialist.domain.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	List<Restaurant> findByNameContaining(String name);
}
