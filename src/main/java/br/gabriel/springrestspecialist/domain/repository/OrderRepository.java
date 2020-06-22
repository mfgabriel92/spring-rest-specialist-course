package br.gabriel.springrestspecialist.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import br.gabriel.springrestspecialist.domain.model.Order;

public interface OrderRepository extends BaseJpaRepository<Order, Integer> {
    @Query("FROM Order o JOIN FETCH o.user JOIN FETCH o.restaurant r JOIN FETCH r.cuisine")
    List<Order> findAll();
}