package br.gabriel.springrestspecialist.api.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {
    @ApiModelProperty(required = true)
    @NotBlank
    private String zipCode;
    
    @ApiModelProperty(required = true)
    @NotBlank
    private String streetName;

    @ApiModelProperty(required = true)
    @NotBlank
    private String number;
    
    private String apartment;
    
    @ApiModelProperty(required = true)
    @NotBlank
    private String neighborhood;
    
    @ApiModelProperty(required = true)
    @NotNull
    private Integer cityId;
}
