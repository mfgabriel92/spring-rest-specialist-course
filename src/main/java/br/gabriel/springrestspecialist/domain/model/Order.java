package br.gabriel.springrestspecialist.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

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
	
	@ManyToOne
	private PaymentMethod paymentMethod;
	
	@ManyToOne
	private Restaurant restaurant;
	
	@ManyToOne
	private User user;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@CreationTimestamp
	private OffsetDateTime createdAt;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> items;
	
	public void calculateGrandTotal() {
	    subtotal = getItems().stream()
	        .map(item -> item.getTotalPrice())
	        .reduce(BigDecimal.ZERO, BigDecimal::add);
	   
	    setGrandTotal(getSubtotal().add(getShippingFee()));
	}
	
	public void setOrderToItems() {
	    getItems().forEach(item -> item.setOrder(this));
	}
}
