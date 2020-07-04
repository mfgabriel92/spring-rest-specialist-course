package br.gabriel.springrestspecialist.api.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedDoc<T> {
    private List<T> content;
    
    @ApiModelProperty(value = "Total number of elements by page", example = "20")
    private Integer pageSize;
    
    @ApiModelProperty(value = "Current number or elemebts by page", example = "5")
    private Integer totalElements;
    
    @ApiModelProperty(value = "Total number of pages", example = "12")
    private Integer totalPages;
    
    @ApiModelProperty(value = "Current page number", example = "7")
    private Integer currentPage;
}
