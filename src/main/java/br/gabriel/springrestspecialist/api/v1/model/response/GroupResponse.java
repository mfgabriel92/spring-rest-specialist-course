package br.gabriel.springrestspecialist.api.v1.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "groups")
@Getter
@Setter
public class GroupResponse extends RepresentationModel<GroupResponse> {
    private Integer id;
    
    private String name;
}
