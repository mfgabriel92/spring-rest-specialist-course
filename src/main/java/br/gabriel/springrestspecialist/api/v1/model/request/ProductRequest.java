package br.gabriel.springrestspecialist.api.v1.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    @ApiModelProperty(required = true)
    @NotBlank
    private String name;
    
    @ApiModelProperty(required = true)
    @NotBlank
    private String description;
    
    @ApiModelProperty(required = true)
    @NotNull
    @Positive
    private BigDecimal price;
}
