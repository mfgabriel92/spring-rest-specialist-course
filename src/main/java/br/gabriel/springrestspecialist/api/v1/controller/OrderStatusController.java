package br.gabriel.springrestspecialist.api.v1.controller;

import br.gabriel.springrestspecialist.api.v1.openapi.controller.OrderStatusDoc;
import br.gabriel.springrestspecialist.core.security.Permission;
import br.gabriel.springrestspecialist.domain.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/orders/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderStatusController implements OrderStatusDoc {
    @Autowired
    private OrderStatusService service;
    
    @Override
    @Permission.Order.CanAlterStatus
    @PutMapping("confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable String code) {
        service.confirm(code);
    }
    
    @Override
    @Permission.Order.CanAlterStatus
    @PutMapping("deliver")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliver(@PathVariable String code) {
        service.deliver(code);
    }
    
    @Override
    @Permission.Order.CanAlterStatus
    @PutMapping("cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable String code) {
        service.cancel(code);
    }
}
