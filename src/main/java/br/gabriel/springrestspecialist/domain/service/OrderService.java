package br.gabriel.springrestspecialist.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.exception.ApiException;
import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;
import br.gabriel.springrestspecialist.domain.model.City;
import br.gabriel.springrestspecialist.domain.model.Order;
import br.gabriel.springrestspecialist.domain.model.PaymentMethod;
import br.gabriel.springrestspecialist.domain.model.Product;
import br.gabriel.springrestspecialist.domain.model.Restaurant;
import br.gabriel.springrestspecialist.domain.model.User;
import br.gabriel.springrestspecialist.domain.repository.CityRepository;
import br.gabriel.springrestspecialist.domain.repository.OrderRepository;
import br.gabriel.springrestspecialist.domain.repository.PaymentMethodRepository;
import br.gabriel.springrestspecialist.domain.repository.ProductRepository;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;
    
	@Autowired
	private RestaurantRepository restarantRepository;
	
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
	    order.getUser().setId(1);
	    
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
        
        Restaurant restaurant = restarantRepository.findOrFail(restaurantId);
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