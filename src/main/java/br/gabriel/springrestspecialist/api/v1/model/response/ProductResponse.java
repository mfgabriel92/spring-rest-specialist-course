package br.gabriel.springrestspecialist.api.v1.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "products")
@Getter
@Setter
public class ProductResponse extends RepresentationModel<ProductResponse> {
    private Integer id;
    
    private String name;
    
    private String description;
    
    private BigDecimal price;
    
    private boolean active;
}
