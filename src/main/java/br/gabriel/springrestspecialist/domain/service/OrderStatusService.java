package br.gabriel.springrestspecialist.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gabriel.springrestspecialist.domain.model.Order;

@Service
public class OrderStatusService {
    @Autowired
    private OrderService service;
    
    @Transactional
    public void confirm(String code) {
        Order order = service.findByCodeOrFail(code);
        order.confirm();
    }
    
    @Transactional
    public void deliver(String code) {
        Order order = service.findByCodeOrFail(code);
        order.deliver();
    }
    
    @Transactional
    public void cancel(String code) {
        Order order = service.findByCodeOrFail(code);
        order.cancel();
    }
}
