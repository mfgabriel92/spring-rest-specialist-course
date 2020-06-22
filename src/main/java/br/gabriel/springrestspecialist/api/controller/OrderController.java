package br.gabriel.springrestspecialist.api.controller;

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
    public List<OrderSummaryResponse> findAll() {
        return summaryMapper.toCollectionModel(repository.findAll());
    }
    
    @GetMapping("{id}")
    public OrderResponse findById(@PathVariable Integer id) {
        return mapper.toModel(repository.findOrFail(id));
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse save(@RequestBody @Valid OrderRequest orderRequest) {
        Order order = mapper.toDomainObject(orderRequest);
        return mapper.toModel(service.save(order));
    }
}
