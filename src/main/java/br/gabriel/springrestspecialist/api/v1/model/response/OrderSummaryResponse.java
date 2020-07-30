package br.gabriel.springrestspecialist.api.v1.model.response;

import br.gabriel.springrestspecialist.domain.model.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "orders")
@Getter
@Setter
public class OrderSummaryResponse extends RepresentationModel<OrderSummaryResponse> {
    private String code;

    private BigDecimal grandTotal;
    
    private OrderStatus status;
    
    @JsonProperty("client")
    private String userName;
    
    @JsonProperty("restaurant")
    private String restaurantName;
    
    @JsonProperty("cuisine")
    private String restaurantCuisineName;
}
