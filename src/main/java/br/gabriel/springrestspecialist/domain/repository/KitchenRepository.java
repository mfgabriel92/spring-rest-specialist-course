package br.gabriel.springrestspecialist.domain.repository;

import org.springframework.stereotype.Repository;

import br.gabriel.springrestspecialist.domain.model.Kitchen;

@Repository
public interface KitchenRepository extends BaseJpaRepository<Kitchen, Integer> {
}
