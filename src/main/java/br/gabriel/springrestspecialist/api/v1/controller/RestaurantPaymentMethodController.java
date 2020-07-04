package br.gabriel.springrestspecialist.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.api.mapper.PaymentMethodMapper;
import br.gabriel.springrestspecialist.api.v1.model.response.PaymentMethodResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.controller.RestaurantPaymentMethodDoc;
import br.gabriel.springrestspecialist.domain.model.Restaurant;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepository;
import br.gabriel.springrestspecialist.domain.service.RestaurantService;

@RestController
@RequestMapping(path = "/v1/restaurants/{id}/payment-methods", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantPaymentMethodController implements RestaurantPaymentMethodDoc {
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Autowired
    private RestaurantService restaurantService;
    
    @Autowired
    private PaymentMethodMapper mapper;
    
    @Override
    @GetMapping
    public List<PaymentMethodResponse> findAll(@PathVariable Integer id) {
        Restaurant restaurant = restaurantRepository.findOrFail(id);
        return mapper.toCollectionModel(restaurant.getPaymentMethods());
    }
    
    @Override
    @PutMapping("{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPaymentMethod(@PathVariable Integer id, @PathVariable Integer paymentMethodId) {
        restaurantService.addPaymentMethod(id, paymentMethodId);
    }
    
    @Override
    @DeleteMapping("{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePaymentMethod(@PathVariable Integer id, @PathVariable Integer paymentMethodId) {
        restaurantService.removePaymentMethod(id, paymentMethodId);
    }
}
