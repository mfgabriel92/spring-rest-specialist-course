package br.gabriel.springrestspecialist.domain.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import br.gabriel.springrestspecialist.domain.event.OrderConfirmedEvent;
import br.gabriel.springrestspecialist.domain.model.Order;
import br.gabriel.springrestspecialist.domain.service.MailSenderService;
import br.gabriel.springrestspecialist.domain.service.MailSenderService.EMail;

@Component
public class OrderConfirmedListener {
    @Autowired
    private MailSenderService mailSender;
    
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onOrderConfirmed(OrderConfirmedEvent event) {
        Order order = event.getOrder();
        
        EMail mail = EMail.builder()
            .recipient(order.getUser().getEmail())
            .subject(order.getRestaurant().getName() + " - order confirmed")
            .body("order-confirmed.html")
            .variable("order", order)
            .build();
        
        mailSender.send(mail);
    }
}
