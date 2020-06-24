package br.gabriel.springrestspecialist.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.domain.service.OrderStatusService;

@RestController
@RequestMapping("/orders/{code}")
public class OrderStatusController {
    @Autowired
    private OrderStatusService service;
    
    @PutMapping("confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable String code) {
        service.confirm(code);
    }
    
    @PutMapping("deliver")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliver(@PathVariable String code) {
        service.deliver(code);
    }
    
    @PutMapping("cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable String code) {
        service.cancel(code);
    }
}