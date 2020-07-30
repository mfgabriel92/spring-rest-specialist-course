package br.gabriel.springrestspecialist.api.v1.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurants")
@Getter
@Setter
public class RestaurantResponse extends RepresentationModel<RestaurantResponse> {
    private Integer id;
    
    private String name;
    
    private BigDecimal shippingFee;
    
    @JsonProperty("cuisine")
    private String cuisineName;
    
    private Boolean active;
    
    private Boolean open;
    
    private AddressResponse address;
}
