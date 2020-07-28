package br.gabriel.springrestspecialist.api.v1.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class CityResponse extends RepresentationModel<CityResponse> {
    private Integer id;
    
    private String name;
    
    @JsonProperty("state")
    private String stateName;
}
