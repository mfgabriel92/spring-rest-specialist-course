package br.gabriel.springrestspecialist.api.mapper;

import br.gabriel.springrestspecialist.api.v1.controller.OrderController;
import br.gabriel.springrestspecialist.api.v1.model.response.OrderSummaryResponse;
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
public class OrderSummaryMapper implements RepresentationModelAssembler<Order, OrderSummaryResponse> {
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public OrderSummaryResponse toModel(Order order) {
        OrderSummaryResponse response = mapper.map(order, OrderSummaryResponse.class);
        
        response.add(linkTo(methodOn(OrderController.class).findById(order.getCode())).withSelfRel());
        
        return response;
    }
    
    @Override
    public CollectionModel<OrderSummaryResponse> toCollectionModel(Iterable<? extends Order> entities) {
        return RepresentationModelAssembler.super
            .toCollectionModel(entities)
            .add(linkTo(OrderController.class).withRel(IanaLinkRelations.COLLECTION));
    }
}
