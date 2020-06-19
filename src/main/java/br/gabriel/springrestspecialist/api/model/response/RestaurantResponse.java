package br.gabriel.springrestspecialist.api.model.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantResponse {
    private Integer id;
    
    private String name;
    
    private BigDecimal shippingFee;
    
    @JsonProperty("kitchen")
    private String kitchenName;
    
    private Boolean active;
    
    private AddressResponse address;
}