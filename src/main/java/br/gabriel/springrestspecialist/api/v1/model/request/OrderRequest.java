package br.gabriel.springrestspecialist.api.v1.model.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    @ApiModelProperty(required = true)
    @NotNull
    private Integer restaurantId;
    
    @ApiModelProperty(required = true)
    @NotNull
    private Integer paymentMethodId;
    
    @ApiModelProperty(required = true)
    @Valid
    @NotNull
    private AddressRequest deliveryAddress;
    
    @ApiModelProperty(required = true)
    @Valid
    @Size(min = 1)
    @NotNull
    private List<OrderItemRequest> items;
}
