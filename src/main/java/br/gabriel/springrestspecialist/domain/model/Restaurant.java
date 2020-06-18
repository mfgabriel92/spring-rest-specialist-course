package br.gabriel.springrestspecialist.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.gabriel.springrestspecialist.core.validation.FreeShippingFlag;
import br.gabriel.springrestspecialist.core.validation.Groups.KitchenId;
import br.gabriel.springrestspecialist.core.validation.ShippingFee;
import lombok.Data;
import lombok.EqualsAndHashCode;

@FreeShippingFlag(fieldValue = "shippingFee", fieldDescription = "name", mandatoryFlag = "Free shipping!")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "t_restaurants")
public class Restaurant {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	private String name;
	
	@NotNull
	@ShippingFee
	private BigDecimal shippingFee;
	
	@ConvertGroup(from = Default.class, to = KitchenId.class)
	@Valid
	@NotNull
	@ManyToOne
	private Kitchen kitchen;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name = "t_restaurants_payment_methods",
		joinColumns = @JoinColumn(name = "restaurant_id"),
		inverseJoinColumns = @JoinColumn(name = "payment_method_id")
	)
	private List<PaymentMethod> paymentMethods = new ArrayList<>();
	
	@JsonIgnore
	@Embedded
	private Address address;
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurant")
	private List<Product> products = new ArrayList<>();
	
	@JsonIgnore
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@JsonIgnore
	@UpdateTimestamp
	private LocalDateTime updatedAt;
}
