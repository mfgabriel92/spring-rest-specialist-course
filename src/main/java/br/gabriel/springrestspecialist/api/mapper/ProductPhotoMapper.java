package br.gabriel.springrestspecialist.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gabriel.springrestspecialist.api.v1.model.request.ProductPhotoRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.ProductPhotoResponse;
import br.gabriel.springrestspecialist.domain.model.ProductPhoto;

@Component
public class ProductPhotoMapper {
    @Autowired
    private ModelMapper mapper;
    
    public ProductPhotoResponse toModel(ProductPhoto productPhoto) {
        return mapper.map(productPhoto, ProductPhotoResponse.class);
    }
    
    public void copyToDomainObject(ProductPhotoRequest productPhotoRequest, ProductPhoto productPhoto) {
        mapper.map(productPhotoRequest, productPhoto);
    }
}