package br.gabriel.springrestspecialist.api.v1.model.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantIdsRequest {
    @ApiModelProperty(required = true)
    @NotNull
    private List<Integer> ids;
}
