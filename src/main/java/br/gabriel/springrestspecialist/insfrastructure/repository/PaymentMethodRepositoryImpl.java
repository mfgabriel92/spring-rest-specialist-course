package br.gabriel.springrestspecialist.insfrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.model.PaymentMethod;
import br.gabriel.springrestspecialist.domain.repository.PaymentMethodRepository;

@Component
public class PaymentMethodRepositoryImpl implements PaymentMethodRepository {
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<PaymentMethod> findAll() {
		TypedQuery<PaymentMethod> query = manager.createQuery("FROM PaymentMethod", PaymentMethod.class);
		return query.getResultList();
	}
	
	@Override
	@Transactional
	public PaymentMethod save(PaymentMethod paymentMethod) {
		return manager.merge(paymentMethod);
	}
	
	@Override
	public PaymentMethod findById(Integer id) {
		return manager.find(PaymentMethod.class, id);
	}
	
	@Override
	@Transactional
	public void delete(PaymentMethod paymentMethod) {
		paymentMethod = findById(paymentMethod.getId());
		manager.remove(paymentMethod);
	}
}
