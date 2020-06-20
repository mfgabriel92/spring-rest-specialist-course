package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.api.model.mapper.ProductMapper;
import br.gabriel.springrestspecialist.api.model.request.ProductRequest;
import br.gabriel.springrestspecialist.api.model.response.ProductResponse;
import br.gabriel.springrestspecialist.domain.model.Product;
import br.gabriel.springrestspecialist.domain.service.ProductService;

@RestController
@RequestMapping("/restaurants/{id}/products")
public class RestaurantProductController {
    @Autowired
    private ProductService service;
    
    @Autowired
    private ProductMapper mapper;
    
    @GetMapping
    public List<ProductResponse> findAll(@PathVariable Integer id) {
        return mapper.toCollectionModel(service.findAll(id));
    }
    
    @GetMapping("{productId}")
    public ProductResponse findAll(@PathVariable Integer id, @PathVariable Integer productId) {
        return mapper.toModel(service.findOrFail(productId, id));
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse save(@PathVariable Integer id, @RequestBody @Valid ProductRequest productRequest) {
        Product product = mapper.toDomainObject(productRequest);
        return mapper.toModel(service.save(id, product));
    }
    
    @PutMapping("{productId}")
    public ProductResponse save(@PathVariable Integer id, @PathVariable Integer productId, @RequestBody @Valid ProductRequest productRequest) {
        Product product = service.findOrFail(productId, id);
        mapper.copyToDomainObject(productRequest, product);
        return mapper.toModel(service.save(id, product));
    }
}