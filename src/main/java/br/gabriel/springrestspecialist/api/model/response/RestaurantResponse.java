package br.gabriel.springrestspecialist.api.model.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantResponse {
    private Integer id;
    
    private String name;
    
    private BigDecimal shippingFee;
    
    private String kitchenName;
}