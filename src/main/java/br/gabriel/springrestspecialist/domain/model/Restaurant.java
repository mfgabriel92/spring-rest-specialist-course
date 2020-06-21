package br.gabriel.springrestspecialist.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "t_restaurants")
public class Restaurant {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	
	private BigDecimal shippingFee;
	
	@Valid
	@ManyToOne(fetch = FetchType.LAZY)
	private Kitchen kitchen;
	
	@ManyToMany
	@JoinTable(
		name = "t_restaurants_payment_methods",
		joinColumns = @JoinColumn(name = "restaurant_id"),
		inverseJoinColumns = @JoinColumn(name = "payment_method_id")
	)
	private Set<PaymentMethod> paymentMethods = new HashSet<>();
	
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "restaurant")
	private List<Product> products = new ArrayList<>();
	
	private Boolean active = Boolean.TRUE;
	
	private Boolean open = Boolean.TRUE;
	
	@ManyToMany
    @JoinTable(
        name = "t_restaurants_users",
        joinColumns = @JoinColumn(name = "restaurant_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();
	
	@CreationTimestamp
	private OffsetDateTime createdAt;
	
	@UpdateTimestamp
	private OffsetDateTime updatedAt;
	
	public void activate() {
	    setActive(true);
	}
	
	public void deactivate() {
        setActive(false);
    }
	
	public void addPaymentMethod(PaymentMethod paymentMethod) {
	    getPaymentMethods().add(paymentMethod);
	}
	
	public void removePaymentMethod(PaymentMethod paymentMethod) {
        getPaymentMethods().remove(paymentMethod);
    }
	
	public void open() {
        setOpen(true);
    }
    
    public void close() {
        setOpen(false);
    }
    
    public void addUser(User user) {
        getUsers().add(user);
    }
    
    public void removeUser(User user) {
        getUsers().remove(user);
    }
}
