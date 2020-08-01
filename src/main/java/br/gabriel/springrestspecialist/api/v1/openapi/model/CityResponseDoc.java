package br.gabriel.springrestspecialist.api.v1.openapi.model;

import br.gabriel.springrestspecialist.api.v1.openapi.controller.CityDoc;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("CitiesResponse")
@Getter
@Setter
public class CityResponseDoc {
    private Embedded _embedded;
    
    private LinksDoc _links;
    
    @Getter
    @Setter
    private static class Embedded {
        private List<CityDoc> cities;
    }
}
