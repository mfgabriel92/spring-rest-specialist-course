package br.gabriel.springrestspecialist.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {
private String zipCode;
    private String streetName;

    private String number;
    
    private String apartment;
    
    private String neighborhood;
    
    @JsonProperty("city")
    private String cityName;
    
    @JsonProperty("state")
    private String cityStateName;
}
