package br.gabriel.springrestspecialist.api.mapper;

import br.gabriel.springrestspecialist.api.v1.controller.*;
import br.gabriel.springrestspecialist.api.v1.model.request.OrderRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.OrderResponse;
import br.gabriel.springrestspecialist.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderMapper implements RepresentationModelAssembler<Order, OrderResponse> {
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public OrderResponse toModel(Order order) {
        OrderResponse response = mapper.map(order, OrderResponse.class);
        
        response.add(linkTo(methodOn(OrderController.class).findById(order.getCode())).withSelfRel());
        response.getPaymentMethod().add(linkTo(methodOn(PaymentMethodController.class).findById(order.getPaymentMethod().getId())).withSelfRel());
        response.getRestaurant().add(linkTo(methodOn(RestaurantController.class).findById(order.getRestaurant().getId())).withSelfRel());
        response.getUser().add(linkTo(methodOn(UserController.class).findById(order.getUser().getId())).withSelfRel());
        response.getItems().forEach(item -> item.add(linkTo(methodOn(RestaurantProductController.class).findById(order.getRestaurant().getId(), item.getProductId())).withSelfRel()));
        
        return response;
    }
    
    @Override
    public CollectionModel<OrderResponse> toCollectionModel(Iterable<? extends Order> entities) {
        return RepresentationModelAssembler.super
            .toCollectionModel(entities)
            .add(linkTo(OrderController.class).withRel(IanaLinkRelations.COLLECTION));
    }
    
    public Order toDomainObject(OrderRequest orderRequest) {
        return mapper.map(orderRequest, Order.class);
    }
    
    public void copyToDomainObject(OrderRequest orderRequest, Order order) {
        mapper.map(orderRequest, order);
    }
}
