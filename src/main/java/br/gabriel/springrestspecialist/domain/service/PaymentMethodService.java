package br.gabriel.springrestspecialist.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.model.PaymentMethod;
import br.gabriel.springrestspecialist.domain.repository.PaymentMethodRepository;

@Service
public class PaymentMethodService {
    @Autowired
    private PaymentMethodRepository repository;
    
    @Transactional
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return repository.save(paymentMethod);
    }
    
    @Transactional
    public void deleteById(Integer id) {
        repository.deleteOrFail(id);
    }
}
