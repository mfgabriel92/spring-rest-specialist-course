package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.api.model.mapper.OrderMapper;
import br.gabriel.springrestspecialist.api.model.mapper.OrderSummaryMapper;
import br.gabriel.springrestspecialist.api.model.response.OrderResponse;
import br.gabriel.springrestspecialist.api.model.response.OrderSummaryResponse;
import br.gabriel.springrestspecialist.domain.repository.OrderRepository;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository repository;
    
    @Autowired
    private OrderMapper mapper;
    
    @Autowired
    private OrderSummaryMapper summaryMapper;
    
    @GetMapping
    private List<OrderSummaryResponse> findAll() {
        return summaryMapper.toCollectionModel(repository.findAll());
    }
    
    @GetMapping("{id}")
    private OrderResponse findById(@PathVariable Integer id) {
        return mapper.toModel(repository.findOrFail(id));
    }
}
