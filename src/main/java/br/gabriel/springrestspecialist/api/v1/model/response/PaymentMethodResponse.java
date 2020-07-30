package br.gabriel.springrestspecialist.api.v1.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "payment-methods")
@Getter
@Setter
public class PaymentMethodResponse extends RepresentationModel<PaymentMethodResponse> {
    private Integer id;
    
    private String description;
}
