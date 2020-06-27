package br.gabriel.springrestspecialist.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gabriel.springrestspecialist.domain.model.Order;
import br.gabriel.springrestspecialist.domain.service.MailSenderService.EMail;

@Service
public class OrderStatusService {
    @Autowired
    private OrderService service;
    
    @Autowired
    private MailSenderService mailSender;
    
    @Transactional
    public void confirm(String code) {
        Order order = service.findByCodeOrFail(code);
        order.confirm();
        
        EMail mail = EMail.builder()
            .recipient(order.getUser().getEmail())
            .subject(order.getRestaurant().getName() + " - order confirmed")
            .body("Order code <strong>" + order.getCode() + "</strong> has been confirmed and is being prepared")
            .build();
        
        mailSender.send(mail);
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
