package br.gabriel.springrestspecialist.api.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
    @NotNull
    private Integer productId;

    @NotNull
    @Positive
    private Integer quantity;
    
    private String obseravation;
}
