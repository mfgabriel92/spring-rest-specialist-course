package br.gabriel.springrestspecialist.api.v1.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "users")
@Getter
@Setter
public class UserResponse extends RepresentationModel<UserResponse> {
    private Integer id; 
    
    private String name;
    
    private String email;
}
