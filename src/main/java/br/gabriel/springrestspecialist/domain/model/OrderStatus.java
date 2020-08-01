package br.gabriel.springrestspecialist.domain.model;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus {
	CREATED,
	CONFIRMED(CREATED),
	DELIVERED(CONFIRMED),
	CANCELED(CREATED);
    
    private final List<OrderStatus> allowedStatuses;

    OrderStatus(OrderStatus... allowedStatuses) {
        this.allowedStatuses = Arrays.asList(allowedStatuses);
    }
   
    public boolean canAlterTo(OrderStatus status) {
        return status.allowedStatuses.contains(this);
    }
}
