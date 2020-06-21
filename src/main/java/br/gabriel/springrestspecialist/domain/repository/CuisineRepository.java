package br.gabriel.springrestspecialist.domain.repository;

import org.springframework.stereotype.Repository;

import br.gabriel.springrestspecialist.domain.model.Cuisine;

@Repository
public interface CuisineRepository extends BaseJpaRepository<Cuisine, Integer> {
}
