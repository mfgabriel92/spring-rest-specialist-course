package br.gabriel.springrestspecialist.api.v1.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "products")
@Getter
@Setter
public class OrderItemResponse extends RepresentationModel<OrderItemResponse> {
    private Integer productId;
    
    private String productName;

    private Integer quantity;
    
    private BigDecimal productPrice;
    
    private BigDecimal totalPrice;
}
