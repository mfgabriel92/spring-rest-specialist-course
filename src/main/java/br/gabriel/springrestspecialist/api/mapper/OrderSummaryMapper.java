package br.gabriel.springrestspecialist.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gabriel.springrestspecialist.api.v1.model.response.OrderSummaryResponse;
import br.gabriel.springrestspecialist.domain.model.Order;

@Component
public class OrderSummaryMapper {
    @Autowired
    private ModelMapper mapper;
    
    public OrderSummaryResponse toModel(Order order) {
        return mapper.map(order, OrderSummaryResponse.class);
    }
    
    public List<OrderSummaryResponse> toCollectionModel(List<Order> orders) {
        return orders.stream().map(order -> toModel(order)).collect(Collectors.toList());
    }
}