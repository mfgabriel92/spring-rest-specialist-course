package br.gabriel.springrestspecialist.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "restaurants")
public class Restaurant {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private BigDecimal shippingFee;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Kitchen kitchen;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name = "restaurants_payment_methods",
		joinColumns = @JoinColumn(name = "restaurant_id"),
		inverseJoinColumns = @JoinColumn(name = "payment_method_id")
	)
	private List<PaymentMethod> paymentMethods = new ArrayList<>();
	
	@JsonIgnore
	@Embedded
	private Address address;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime createdAt;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime updatedAt;
}
