package br.gabriel.springrestspecialist.api.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityRequest {
    @NotNull
    private String name;
    
    @NotNull
    private Integer stateId;
}
