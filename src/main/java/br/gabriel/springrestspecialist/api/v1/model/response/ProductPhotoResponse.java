package br.gabriel.springrestspecialist.api.v1.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class ProductPhotoResponse extends RepresentationModel<ProductPhotoResponse> {
    private String filename;
    
    private String description;
    
    private String contentType;
    
    private Integer size; 
}
