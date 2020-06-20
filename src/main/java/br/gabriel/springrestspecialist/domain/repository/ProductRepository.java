package br.gabriel.springrestspecialist.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import br.gabriel.springrestspecialist.domain.model.Product;

public interface ProductRepository extends BaseJpaRepository<Product, Integer> {
    @Query("FROM Product WHERE id = :id AND restaurant.id = :restaurantId")
    Optional<Product> findById(Integer id, Integer restaurantId);
}