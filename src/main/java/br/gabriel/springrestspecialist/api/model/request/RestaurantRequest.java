package br.gabriel.springrestspecialist.api.model.request;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.gabriel.springrestspecialist.core.validation.FreeShippingFlag;
import br.gabriel.springrestspecialist.core.validation.ShippingFee;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@FreeShippingFlag(fieldValue = "shippingFee", fieldDescription = "name", mandatoryFlag = "Free shipping!")
@Getter
@Setter
public class RestaurantRequest {
    @ApiModelProperty(required = true)
    @NotBlank
    private String name;
    
    @ApiModelProperty(required = true)
    @NotNull
    @ShippingFee
    private BigDecimal shippingFee;
    
    @ApiModelProperty(required = true)
    @NotNull
    private Integer cuisineId;
    
    @ApiModelProperty(required = true)
    @Valid
    @NotNull
    private AddressRequest address;
}