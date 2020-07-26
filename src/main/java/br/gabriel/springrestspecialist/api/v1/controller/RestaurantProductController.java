package br.gabriel.springrestspecialist.api.v1.controller;

import br.gabriel.springrestspecialist.api.mapper.ProductMapper;
import br.gabriel.springrestspecialist.api.v1.model.request.ProductRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.ProductResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.controller.RestaurantProductDoc;
import br.gabriel.springrestspecialist.core.security.Permission;
import br.gabriel.springrestspecialist.domain.model.Product;
import br.gabriel.springrestspecialist.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/restaurants/{id}/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantProductController implements RestaurantProductDoc {
    @Autowired
    private ProductService service;
    
    @Autowired
    private ProductMapper mapper;
    
    @Override
    @GetMapping
    public List<ProductResponse> findAll(@PathVariable Integer id) {
        return mapper.toCollectionModel(service.findAll(id));
    }
    
    @Override
    @GetMapping("{productId}")
    public ProductResponse findById(@PathVariable Integer id, @PathVariable Integer productId) {
        return mapper.toModel(service.findOrFail(productId, id));
    }
    
    @Override
    @Permission.Restaurant.CanAlterStatus
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse save(@PathVariable Integer id, @RequestBody @Valid ProductRequest productRequest) {
        Product product = mapper.toDomainObject(productRequest);
        return mapper.toModel(service.save(id, product));
    }
    
    @Override
    @Permission.Restaurant.CanAlterStatus
    @PutMapping("{productId}")
    public ProductResponse save(@PathVariable Integer id, @PathVariable Integer productId, @RequestBody @Valid ProductRequest productRequest) {
        Product product = service.findOrFail(productId, id);
        mapper.copyToDomainObject(productRequest, product);
        return mapper.toModel(service.save(id, product));
    }
}
