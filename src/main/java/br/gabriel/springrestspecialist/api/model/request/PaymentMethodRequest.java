package br.gabriel.springrestspecialist.api.model.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethodRequest {
    @ApiModelProperty(required = true)
    @NotNull
    private String description;
}
