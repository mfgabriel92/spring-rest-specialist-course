package br.gabriel.springrestspecialist.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
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
	
	private LocalDateTime confirmedAt;
	
	private LocalDateTime canceledAt;
	
	private LocalDateTime deliveredAt;
	
	@Embedded
	private Address deliveryAddress;
	
	@ManyToOne
	private PaymentMethod paymentMethod;
	
	@ManyToOne
	private Restaurant restaurant;
	
	@ManyToOne
	private User user;
	
	private OrderStatus status;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> items;
}
