package br.gabriel.springrestspecialist.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gabriel.springrestspecialist.domain.model.Order;
import br.gabriel.springrestspecialist.domain.repository.OrderRepository;

@Service
public class OrderStatusService {
    @Autowired
    private OrderRepository orderRepository;
    
    @Transactional
    public void confirm(Integer id) {
        Order order = orderRepository.findOrFail(id);
        order.confirm();
    }
    
    @Transactional
    public void deliver(Integer id) {
        Order order = orderRepository.findOrFail(id);
        order.deliver();
    }
    
    @Transactional
    public void cancel(Integer id) {
        Order order = orderRepository.findOrFail(id);
        order.cancel();
    }
}
