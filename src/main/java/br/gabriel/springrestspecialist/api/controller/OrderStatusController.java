package br.gabriel.springrestspecialist.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.api.controller.documentation.OrderStatusDoc;
import br.gabriel.springrestspecialist.domain.service.OrderStatusService;

@RestController
@RequestMapping(path = "/orders/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderStatusController implements OrderStatusDoc {
    @Autowired
    private OrderStatusService service;
    
    @Override
    @PutMapping("confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable String code) {
        service.confirm(code);
    }
    
    @Override
    @PutMapping("deliver")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliver(@PathVariable String code) {
        service.deliver(code);
    }
    
    @Override
    @PutMapping("cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable String code) {
        service.cancel(code);
    }
}
