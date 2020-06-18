package br.gabriel.springrestspecialist.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResponse {
    private Integer id;
    
    private String name;
    
    @JsonProperty("state")
    private String stateName;
}
