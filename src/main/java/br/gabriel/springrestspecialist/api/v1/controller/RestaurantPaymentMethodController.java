package br.gabriel.springrestspecialist.api.v1.controller;

import br.gabriel.springrestspecialist.api.mapper.PaymentMethodMapper;
import br.gabriel.springrestspecialist.api.v1.model.response.PaymentMethodResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.controller.RestaurantPaymentMethodDoc;
import br.gabriel.springrestspecialist.domain.model.Restaurant;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepository;
import br.gabriel.springrestspecialist.domain.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public CollectionModel<PaymentMethodResponse> findAll(@PathVariable Integer id) {
        Restaurant restaurant = restaurantRepository.findOrFail(id);
        CollectionModel<PaymentMethodResponse> paymentMethodResponses = mapper.toCollectionModel(restaurant.getPaymentMethods());
        
        paymentMethodResponses.getContent().forEach(paymentMethodResponse ->
            paymentMethodResponse.add(linkTo(methodOn(RestaurantPaymentMethodController.class)
                .removePaymentMethod(id, paymentMethodResponse.getId()))
                .withRel("remove-payment-method")));
        
        return paymentMethodResponses;
    }
    
    @Override
    @PutMapping("{paymentMethodId}")
    public ResponseEntity<Void> addPaymentMethod(@PathVariable Integer id, @PathVariable Integer paymentMethodId) {
        restaurantService.addPaymentMethod(id, paymentMethodId);
        return ResponseEntity.noContent().build();
    }
    
    @Override
    @DeleteMapping("{paymentMethodId}")
    public ResponseEntity<Void> removePaymentMethod(@PathVariable Integer id, @PathVariable Integer paymentMethodId) {
        restaurantService.removePaymentMethod(id, paymentMethodId);
        return ResponseEntity.noContent().build();
    }
}
