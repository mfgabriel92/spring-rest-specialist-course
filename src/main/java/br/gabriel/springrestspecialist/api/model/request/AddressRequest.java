package br.gabriel.springrestspecialist.api.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {
    @NotBlank
    private String zipCode;
    
    @NotBlank
    private String streetName;

    @NotBlank
    private String number;
    
    private String apartment;
    
    @NotBlank
    private String neighborhood;
    
    @NotNull
    private Integer cityId;
}
