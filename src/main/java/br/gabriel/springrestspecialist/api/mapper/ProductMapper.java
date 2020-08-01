package br.gabriel.springrestspecialist.api.mapper;

import br.gabriel.springrestspecialist.api.v1.controller.RestaurantProductController;
import br.gabriel.springrestspecialist.api.v1.model.request.ProductRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.ProductResponse;
import br.gabriel.springrestspecialist.domain.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductMapper implements RepresentationModelAssembler<Product, ProductResponse> {
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public ProductResponse toModel(Product product) {
        ProductResponse response = mapper.map(product, ProductResponse.class);
    
        response.add(linkTo(methodOn(RestaurantProductController.class).findById(product.getRestaurant().getId(), product.getId())).withSelfRel());
        
        return response;
    }
    
//    @Override
//    public CollectionModel<ProductResponse> toCollectionModel(Iterable<? extends Product> entities) {
//        return RepresentationModelAssembler.super
//            .toCollectionModel(entities)
//            .add(linkTo(RestaurantProductController.class).withRel(IanaLinkRelations.COLLECTION));
//    }
    
    public Product toDomainObject(ProductRequest productRequest) {
        return mapper.map(productRequest, Product.class);
    }
    
    public void copyToDomainObject(ProductRequest productRequest, Product product) {
        mapper.map(productRequest, product);
    }
}
