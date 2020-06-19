package br.gabriel.springrestspecialist.api.model.request;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.gabriel.springrestspecialist.core.validation.FreeShippingFlag;
import br.gabriel.springrestspecialist.core.validation.ShippingFee;
import lombok.Getter;
import lombok.Setter;

@FreeShippingFlag(fieldValue = "shippingFee", fieldDescription = "name", mandatoryFlag = "Free shipping!")
@Getter
@Setter
public class RestaurantRequest {
    @NotBlank
    private String name;
    
    @NotNull
    @ShippingFee
    private BigDecimal shippingFee;
    
    @NotNull
    private Integer kitchenId;
    
    @Valid
    @NotNull
    private AddressRequest address;
}