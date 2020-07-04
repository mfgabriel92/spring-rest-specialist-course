package br.gabriel.springrestspecialist.api.v1.model.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantSummaryResponse {
    private Integer id;
    
    private String name;
    
    private BigDecimal shippingFee;
    
    @JsonProperty("cuisine")
    private String cuisineName;
    
    private Boolean active;
}