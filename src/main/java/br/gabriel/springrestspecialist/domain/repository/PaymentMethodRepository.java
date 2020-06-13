package br.gabriel.springrestspecialist.domain.repository;

import br.gabriel.springrestspecialist.domain.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
}
