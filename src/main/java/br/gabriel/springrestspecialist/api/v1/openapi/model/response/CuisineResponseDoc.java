package br.gabriel.springrestspecialist.api.v1.openapi.model.response;

import br.gabriel.springrestspecialist.api.v1.openapi.controller.CityDoc;
import br.gabriel.springrestspecialist.api.v1.openapi.model.LinksDoc;
import br.gabriel.springrestspecialist.api.v1.openapi.model.PagedModelDoc;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("CuisinesResponse")
@Getter
@Setter
public class CuisineResponseDoc {
    private Embedded _embedded;
    
    private LinksDoc _links;
    
    private PagedModelDoc page;
    
    @Getter
    @Setter
    private static class Embedded {
        private List<CityDoc> cuisines;
    }
}
