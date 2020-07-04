package br.gabriel.springrestspecialist.api.v1.model.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityRequest {
    @ApiModelProperty(required = true)
    @NotNull
    private String name;
    
    @ApiModelProperty(required = true)
    @NotNull
    private Integer stateId;
}
