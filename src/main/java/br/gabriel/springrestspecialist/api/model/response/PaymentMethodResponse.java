package br.gabriel.springrestspecialist.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethodResponse {
    private Integer id;
    
    private String description;
}
