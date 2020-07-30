package br.gabriel.springrestspecialist.api.v1.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "states")
@Getter
@Setter
public class StateResponse extends RepresentationModel<StateResponse> {
    private Integer id;
    
    private String name;
}
