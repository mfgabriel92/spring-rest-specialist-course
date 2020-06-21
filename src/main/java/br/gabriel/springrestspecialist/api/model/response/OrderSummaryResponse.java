package br.gabriel.springrestspecialist.api.model.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.gabriel.springrestspecialist.domain.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSummaryResponse {
    private Integer id;

    private BigDecimal grandTotal;
    
    private OrderStatus status;
    
    @JsonProperty("client")
    private String userName;
    
    @JsonProperty("restaurant")
    private String restaurantName;
    
    @JsonProperty("cuisine")
    private String restaurantCuisineName;
}
