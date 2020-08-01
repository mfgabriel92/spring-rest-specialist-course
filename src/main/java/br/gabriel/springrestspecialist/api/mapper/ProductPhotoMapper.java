package br.gabriel.springrestspecialist.api.mapper;

import br.gabriel.springrestspecialist.api.v1.controller.RestaurantProductController;
import br.gabriel.springrestspecialist.api.v1.model.request.ProductPhotoRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.ProductPhotoResponse;
import br.gabriel.springrestspecialist.domain.model.ProductPhoto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductPhotoMapper implements RepresentationModelAssembler<ProductPhoto, ProductPhotoResponse> {
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public ProductPhotoResponse toModel(ProductPhoto productPhoto) {
        ProductPhotoResponse response = mapper.map(productPhoto, ProductPhotoResponse.class);
        
        Integer id = productPhoto.getProduct().getRestaurant().getId();
        Integer productId = productPhoto.getProduct().getId();
        
        response.add(linkTo(methodOn(RestaurantProductController.class).findById(id, productId)).withRel("product"));
        
        return response;
    }
    
    public void copyToDomainObject(ProductPhotoRequest productPhotoRequest, ProductPhoto productPhoto) {
        mapper.map(productPhotoRequest, productPhoto);
    }
}
