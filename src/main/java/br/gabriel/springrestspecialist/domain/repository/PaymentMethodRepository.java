package br.gabriel.springrestspecialist.domain.repository;

import java.util.List;

import br.gabriel.springrestspecialist.domain.model.PaymentMethod;

public interface PaymentMethodRepository {
	List<PaymentMethod> findAll();
	
	PaymentMethod findById(Integer id);
	
	PaymentMethod save(PaymentMethod paymenMethod);
	
	void delete(PaymentMethod paymenMethod);
}
