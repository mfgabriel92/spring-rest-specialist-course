package br.gabriel.springrestspecialist.api.v1.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("PagedModel")
@Getter
@Setter
public class PagedModelDoc {
    @ApiModelProperty(value = "Total number of elements by page", example = "20")
    private Integer pageSize;
    
    @ApiModelProperty(value = "Current number or elements by page", example = "5")
    private Integer totalElements;
    
    @ApiModelProperty(value = "Total number of pages", example = "12")
    private Integer totalPages;
    
    @ApiModelProperty(value = "Current page number", example = "7")
    private Integer currentPage;
}
