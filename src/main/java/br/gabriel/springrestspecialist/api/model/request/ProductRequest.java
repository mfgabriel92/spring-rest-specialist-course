package br.gabriel.springrestspecialist.api.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    @NotBlank
    private String name;
    
    @NotBlank
    private String description;
    
    @NotNull
    @Positive
    private BigDecimal price;
}
