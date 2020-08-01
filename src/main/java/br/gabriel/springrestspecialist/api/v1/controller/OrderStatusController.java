package br.gabriel.springrestspecialist.api.v1.controller;

import br.gabriel.springrestspecialist.api.v1.openapi.controller.OrderStatusDoc;
import br.gabriel.springrestspecialist.core.security.Permission;
import br.gabriel.springrestspecialist.domain.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/orders/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderStatusController implements OrderStatusDoc {
    @Autowired
    private OrderStatusService service;
    
    @Override
    @Permission.Order.CanAlterStatus
    @PutMapping("confirm")
    public ResponseEntity<Void> confirm(@PathVariable String code) {
        service.confirm(code);
        return ResponseEntity.noContent().build();
    }
    
    @Override
    @Permission.Order.CanAlterStatus
    @PutMapping("deliver")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deliver(@PathVariable String code) {
        service.deliver(code);
        return ResponseEntity.noContent().build();
    }
    
    @Override
    @Permission.Order.CanAlterStatus
    @PutMapping("cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancel(@PathVariable String code) {
        service.cancel(code);
        return ResponseEntity.noContent().build();
    }
}
