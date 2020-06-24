package br.gabriel.springrestspecialist.api.controller;

import static br.gabriel.springrestspecialist.infrastructure.repository.spec.OrderSpecs.filteringBy;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.api.model.mapper.OrderMapper;
import br.gabriel.springrestspecialist.api.model.mapper.OrderSummaryMapper;
import br.gabriel.springrestspecialist.api.model.request.OrderRequest;
import br.gabriel.springrestspecialist.api.model.response.OrderResponse;
import br.gabriel.springrestspecialist.api.model.response.OrderSummaryResponse;
import br.gabriel.springrestspecialist.domain.model.Order;
import br.gabriel.springrestspecialist.domain.repository.OrderRepository;
import br.gabriel.springrestspecialist.domain.repository.filter.OrderFilter;
import br.gabriel.springrestspecialist.domain.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository repository;
    
    @Autowired
    private OrderService service;
    
    @Autowired
    private OrderMapper mapper;
    
    @Autowired
    private OrderSummaryMapper summaryMapper;
    
    @GetMapping
    public List<OrderSummaryResponse> findAll(OrderFilter filter) {
        List<Order> orders = repository.findAll(filteringBy(filter));
        return summaryMapper.toCollectionModel(orders);
    }
    
    @GetMapping("{code}")
    public OrderResponse findById(@PathVariable String code) {
        return mapper.toModel(service.findByCodeOrFail(code));
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse save(@RequestBody @Valid OrderRequest orderRequest) {
        Order order = mapper.toDomainObject(orderRequest);
        return mapper.toModel(service.save(order));
    }
}
