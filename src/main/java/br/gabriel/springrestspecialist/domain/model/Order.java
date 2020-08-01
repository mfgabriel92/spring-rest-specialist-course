package br.gabriel.springrestspecialist.domain.model;

import br.gabriel.springrestspecialist.domain.event.OrderConfirmedEvent;
import br.gabriel.springrestspecialist.domain.exception.ApiException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "t_orders")
public class Order extends AbstractAggregateRoot<Order> {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String code;

	private BigDecimal shippingFee;
	
	private BigDecimal subtotal;
	
	private BigDecimal grandTotal;
	
	private OffsetDateTime confirmedAt;
	
	private OffsetDateTime canceledAt;
	
	private OffsetDateTime deliveredAt;
	
	@Embedded
	private Address deliveryAddress;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private PaymentMethod paymentMethod;
	
	@ManyToOne
	private Restaurant restaurant;
	
	@ManyToOne
	private User user;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status = OrderStatus.CREATED;
	
	@CreationTimestamp
	private OffsetDateTime createdAt;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> items;
	
	public void calculateTotals() {
	    subtotal = getItems().stream()
	        .map(OrderItem::getTotalPrice)
	        .reduce(BigDecimal.ZERO, BigDecimal::add);
	    
	    setGrandTotal(getSubtotal().add(getShippingFee()));
	}
	
	public void confirm() {
	    setStatus(OrderStatus.CONFIRMED);
	    setConfirmedAt(OffsetDateTime.now());
	    
	    registerEvent(new OrderConfirmedEvent(this));
	}
	
	public void deliver() {
        setStatus(OrderStatus.DELIVERED);
        setDeliveredAt(OffsetDateTime.now());
    }
	
	public void cancel() {
        setStatus(OrderStatus.CANCELED);
        setCanceledAt(OffsetDateTime.now());
    }
    
    public Boolean canConfirm() {
		return getStatus().canAlterTo(OrderStatus.CONFIRMED);
	}
	
	public Boolean canDeliver() {
		return getStatus().canAlterTo(OrderStatus.DELIVERED);
	}
	
	public Boolean canCancel() {
		return getStatus().canAlterTo(OrderStatus.CANCELED);
	}
	
	private void setStatus(OrderStatus status) {
	    if (!getStatus().canAlterTo(status)) {
	        throw new ApiException(String.format(
	            "Cannot alter from status %s to %s",
	            getStatus(),
	            status
	        ));
	    }
	    
	    this.status = status;
	}
	
	@PrePersist
	private void setCode() {
	    this.code = UUID.randomUUID().toString();
	}
}
