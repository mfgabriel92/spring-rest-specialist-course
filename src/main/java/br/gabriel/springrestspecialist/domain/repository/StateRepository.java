package br.gabriel.springrestspecialist.domain.repository;

import br.gabriel.springrestspecialist.domain.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Integer> {
}
