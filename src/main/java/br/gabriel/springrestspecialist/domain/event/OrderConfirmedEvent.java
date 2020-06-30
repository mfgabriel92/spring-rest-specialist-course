package br.gabriel.springrestspecialist.domain.event;

import br.gabriel.springrestspecialist.domain.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderConfirmedEvent {
    private Order order;
}
