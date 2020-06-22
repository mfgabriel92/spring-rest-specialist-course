package br.gabriel.springrestspecialist.api.model.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    @NotNull
    private Integer restaurantId;
    
    @NotNull
    private Integer paymentMethodId;
    
    @Valid
    @NotNull
    private AddressRequest deliveryAddress;
    
    @Valid
    @Size(min = 1)
    @NotNull
    private List<OrderItemRequest> items;
}
