package br.gabriel.springrestspecialist.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "t_order_items")
public class OrderItem {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer quantity;
	
	private BigDecimal unitPrice;
	
	private BigDecimal totalPrice;
	
	private String observation;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private Order order;
	
	public void calculateTotals() {
	    setUnitPrice(product.getPrice());
	    setTotalPrice(getUnitPrice().multiply(new BigDecimal(quantity)));
	}
}
