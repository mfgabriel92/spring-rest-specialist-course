package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.api.controller.documentation.RestaurantPaymentMethodDoc;
import br.gabriel.springrestspecialist.api.model.mapper.PaymentMethodMapper;
import br.gabriel.springrestspecialist.api.model.response.PaymentMethodResponse;
import br.gabriel.springrestspecialist.domain.model.Restaurant;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepository;
import br.gabriel.springrestspecialist.domain.service.RestaurantService;
import io.swagger.annotations.Api;

@Api(tags = "Restaurant payment method")
@RestController
@RequestMapping("/restaurants/{id}/payment-methods")
public class RestaurantPaymentMethodController implements RestaurantPaymentMethodDoc {
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Autowired
    private RestaurantService restaurantService;
    
    @Autowired
    private PaymentMethodMapper mapper;
    
    @GetMapping
    public List<PaymentMethodResponse> findAll(@PathVariable Integer id) {
        Restaurant restaurant = restaurantRepository.findOrFail(id);
        return mapper.toCollectionModel(restaurant.getPaymentMethods());
    }
    
    @PutMapping("{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPaymentMethod(@PathVariable Integer id, @PathVariable Integer paymentMethodId) {
        restaurantService.addPaymentMethod(id, paymentMethodId);
    }
    
    @DeleteMapping("{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePaymentMethod(@PathVariable Integer id, @PathVariable Integer paymentMethodId) {
        restaurantService.removePaymentMethod(id, paymentMethodId);
    }
}
