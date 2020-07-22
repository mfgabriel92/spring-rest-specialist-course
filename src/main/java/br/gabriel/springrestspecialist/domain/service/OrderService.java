package br.gabriel.springrestspecialist.domain.service;

import br.gabriel.springrestspecialist.domain.exception.ApiException;
import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;
import br.gabriel.springrestspecialist.domain.model.*;
import br.gabriel.springrestspecialist.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

import static br.gabriel.springrestspecialist.core.security.WebSecurity.getLoggedUserId;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;
    
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
    private PaymentMethodRepository paymentMethodRepository;
	
	@Autowired
    private ProductRepository productRepository;
	
	@Autowired
    private CityRepository cityRepository;
	
	public Order findByCodeOrFail(String code) {
	    return repository.findByCode(code)
	        .orElseThrow(() -> new ResourceNotFoundExeption(String.format(
                "The order code %s was not found",
                code
            )
        ));
	}
	
	@Transactional
	public Order save(Order order) {
	    validateAddress(order);
	    validatePaymentMethod(order);
	    validateItems(order);
	    
	    order.setCreatedAt(OffsetDateTime.now());
	    order.setUser(new User());
	    order.getUser().setId(getLoggedUserId().intValue());

		return repository.save(order);
	}
	
	private void validateAddress(Order order) {
	    Integer cityId = order.getDeliveryAddress().getCity().getId();
        City city = cityRepository.findOrFail(cityId);
        order.getDeliveryAddress().setCity(city);
	}
	
	private void validatePaymentMethod(Order order) {
	    Integer restaurantId = order.getRestaurant().getId();
        Integer paymentMethodId = order.getPaymentMethod().getId();
        
        Restaurant restaurant = restaurantRepository.findOrFail(restaurantId);
        PaymentMethod paymentMethod = paymentMethodRepository.findOrFail(paymentMethodId);
        
	    if (!restaurant.acceptsPaymentMethod(paymentMethod)) {
	        throw new ApiException(String.format(
	            "The payment method '%s' is not accepted by the restaurant",
	            paymentMethod.getDescription()
	        ));
	    }
	    
	    order.setRestaurant(restaurant);
        order.setPaymentMethod(paymentMethod);
        order.setShippingFee(restaurant.getShippingFee());
	}
	
	private void validateItems(Order order) {
	    order.getItems().forEach(item -> {
	        Integer productId = item.getProduct().getId();
	        Integer restaurantId = order.getRestaurant().getId();
	        Product product = productRepository.findById(productId, restaurantId)
	            .orElseThrow(() -> new ApiException(String.format(
	                "The restaurant does not have the product of ID %d",
	                item.getProduct().getId()
	            )));
	        
	        item.setOrder(order);
	        item.setProduct(product);
	        item.calculateTotals();
	    });
	    
	    order.calculateTotals();
	}
}
