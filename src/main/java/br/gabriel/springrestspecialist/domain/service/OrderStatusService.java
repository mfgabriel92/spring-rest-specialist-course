package br.gabriel.springrestspecialist.domain.service;

import br.gabriel.springrestspecialist.domain.model.Order;
import br.gabriel.springrestspecialist.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrderStatusService {
    @Autowired
    private OrderService service;
    
    @Autowired
    private OrderRepository repository;
    
    @Transactional
    public ResponseEntity<Void> confirm(String code) {
        Order order = service.findByCodeOrFail(code);
        order.confirm();
        repository.save(order);
        return ResponseEntity.noContent().build();
    }
    
    @Transactional
    public ResponseEntity<Void> deliver(String code) {
        Order order = service.findByCodeOrFail(code);
        order.deliver();
        return ResponseEntity.noContent().build();
    }
    
    @Transactional
    public ResponseEntity<Void> cancel(String code) {
        Order order = service.findByCodeOrFail(code);
        order.cancel();
        return ResponseEntity.noContent().build();
    }
}
