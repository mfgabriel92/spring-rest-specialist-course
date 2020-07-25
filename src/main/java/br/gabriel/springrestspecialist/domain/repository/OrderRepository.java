package br.gabriel.springrestspecialist.domain.repository;

import br.gabriel.springrestspecialist.domain.model.Order;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends BaseJpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
    @Query("FROM Order o JOIN FETCH o.user JOIN FETCH o.restaurant r JOIN FETCH r.cuisine")
    List<Order> findAll();
    
    Optional<Order> findByCode(String code);
}
