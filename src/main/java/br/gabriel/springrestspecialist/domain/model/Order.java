package br.gabriel.springrestspecialist.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import br.gabriel.springrestspecialist.domain.exception.ApiException;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "t_orders")
public class Order {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

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
	        .map(item -> item.getTotalPrice())
	        .reduce(BigDecimal.ZERO, BigDecimal::add);
	    
	    setGrandTotal(getSubtotal().add(getShippingFee()));
	}
	
	public void setOrderToItems() {
	    getItems().forEach(item -> item.setOrder(this));
	}
	
	public void confirm() {
	    setStatus(OrderStatus.CONFIRMED);
	    setConfirmedAt(OffsetDateTime.now());
	}
	
	public void deliver() {
        setStatus(OrderStatus.DELIVERED);
        setDeliveredAt(OffsetDateTime.now());
    }
	
	public void cancel() {
        setStatus(OrderStatus.CANCELED);
        setCanceledAt(OffsetDateTime.now());
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
}
