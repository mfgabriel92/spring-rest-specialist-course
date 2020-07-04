package br.gabriel.springrestspecialist.api.v1.model.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponse {
    private Integer productId;
    
    private String productName;

    private Integer quantity;
    
    private BigDecimal productPrice;
    
    private BigDecimal totalPrice;
}
