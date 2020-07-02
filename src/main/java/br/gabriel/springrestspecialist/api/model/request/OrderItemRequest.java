package br.gabriel.springrestspecialist.api.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
    @ApiModelProperty(required = true)
    @NotNull
    private Integer productId;

    @ApiModelProperty(required = true)
    @NotNull
    @Positive
    private Integer quantity;
    
    private String obseravation;
}
