package br.gabriel.springrestspecialist.api.v1.model.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private Integer id;
    
    private String name;
    
    private String description;
    
    private BigDecimal price;
    
    private boolean active;
}
