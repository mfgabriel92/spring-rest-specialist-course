package br.gabriel.springrestspecialist.api.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gabriel.springrestspecialist.api.model.response.OrderResponse;
import br.gabriel.springrestspecialist.domain.model.Order;

@Component
public class OrderMapper {
    @Autowired
    private ModelMapper mapper;
    
    public OrderResponse toModel(Order order) {
        return mapper.map(order, OrderResponse.class);
    }
    
    public List<OrderResponse> toCollectionModel(List<Order> orders) {
        return orders.stream().map(order -> toModel(order)).collect(Collectors.toList());
    }
    
//    public Order toDomainObject(OrderRequest orderRequest) {
//        return mapper.map(orderRequest, Order.class);
//    }
//    
//    public void copyToDomainObject(OrderRequest orderRequest, Order order) {
//        mapper.map(orderRequest, order);
//    }
}