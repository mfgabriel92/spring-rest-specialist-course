package br.gabriel.springrestspecialist.domain.repository;

import java.util.Optional;

import br.gabriel.springrestspecialist.domain.model.User;

public interface UserRepository extends BaseJpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
