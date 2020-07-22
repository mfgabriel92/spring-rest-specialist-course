package br.gabriel.springrestspecialist.api.v1.controller;

import br.gabriel.springrestspecialist.api.mapper.OrderMapper;
import br.gabriel.springrestspecialist.api.mapper.OrderSummaryMapper;
import br.gabriel.springrestspecialist.api.v1.model.request.OrderRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.OrderResponse;
import br.gabriel.springrestspecialist.api.v1.model.response.OrderSummaryResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.controller.OrderDoc;
import br.gabriel.springrestspecialist.domain.filter.OrderFilter;
import br.gabriel.springrestspecialist.domain.model.Order;
import br.gabriel.springrestspecialist.domain.model.User;
import br.gabriel.springrestspecialist.domain.repository.OrderRepository;
import br.gabriel.springrestspecialist.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static br.gabriel.springrestspecialist.core.security.WebSecurity.getLoggedUserId;
import static br.gabriel.springrestspecialist.infrastructure.repository.spec.OrderSpecs.filteringBy;

@RestController
@RequestMapping(path = "/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController implements OrderDoc {
    @Autowired
    private OrderRepository repository;
    
    @Autowired
    private OrderService service;
    
    @Autowired
    private OrderMapper mapper;
    
    @Autowired
    private OrderSummaryMapper summaryMapper;
    
    @Override
    @GetMapping
    public Page<OrderSummaryResponse> findAll(OrderFilter filter, Pageable pageable) {
        Page<Order> pagedOrders = repository.findAll(filteringBy(filter), pageable);
        List<OrderSummaryResponse> orders = summaryMapper.toCollectionModel(pagedOrders.getContent());
        return new PageImpl<>(orders, pageable, pagedOrders.getTotalElements());
    }
    
    @Override
    @GetMapping("{code}")
    public OrderResponse findById(@PathVariable String code) {
        return mapper.toModel(service.findByCodeOrFail(code));
    }
    
    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse save(@RequestBody @Valid OrderRequest orderRequest) {
        Order order = mapper.toDomainObject(orderRequest);

        order.setUser(new User());
        order.getUser().setId(getLoggedUserId().intValue());

        return mapper.toModel(service.save(order));
    }
}
