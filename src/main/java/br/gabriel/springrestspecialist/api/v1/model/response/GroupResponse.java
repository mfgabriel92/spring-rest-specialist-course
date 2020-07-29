package br.gabriel.springrestspecialist.api.v1.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class GroupResponse extends RepresentationModel<GroupResponse> {
    private Integer id;
    
    private String name;
}
