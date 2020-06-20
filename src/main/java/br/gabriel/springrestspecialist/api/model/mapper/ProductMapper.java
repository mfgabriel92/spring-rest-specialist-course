package br.gabriel.springrestspecialist.api.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gabriel.springrestspecialist.api.model.request.ProductRequest;
import br.gabriel.springrestspecialist.api.model.response.ProductResponse;
import br.gabriel.springrestspecialist.domain.model.Product;

@Component
public class ProductMapper {
    @Autowired
    private ModelMapper mapper;
    
    public ProductResponse toModel(Product product) {
        return mapper.map(product, ProductResponse.class);
    }
    
    public List<ProductResponse> toCollectionModel(List<Product> cities) {
        return cities.stream().map(product -> toModel(product)).collect(Collectors.toList());
    }
    
    public Product toDomainObject(ProductRequest productRequest) {
        return mapper.map(productRequest, Product.class);
    }
    
    public void copyToDomainObject(ProductRequest productRequest, Product product) {
        mapper.map(productRequest, product);
    }
}