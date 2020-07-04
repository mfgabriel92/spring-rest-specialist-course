package br.gabriel.springrestspecialist.api.v1.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Pageable")
@Getter
@Setter
public class PageableDoc {
    @ApiModelProperty(value = "Page number", example = "0")
    private Integer page;
    
    @ApiModelProperty(value = "Results by page", example = "20")
    private Integer size;
    
    @ApiModelProperty(value = "Sort by property", example = "name,desc")
    private List<String> sort;
}
