package br.gabriel.springrestspecialist.api.v1.openapi.model.response;

import br.gabriel.springrestspecialist.api.v1.model.response.OrderSummaryResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.model.LinksDoc;
import br.gabriel.springrestspecialist.api.v1.openapi.model.PagedModelDoc;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("OrderSummariesResponse")
@Getter
@Setter
public class OrderSummaryResponseDoc {
    private Embedded _embedded;
    
    private LinksDoc _links;
    
    private PagedModelDoc page;
    
    @Getter
    @Setter
    private static class Embedded {
        private List<OrderSummaryResponse> orders;
    }
}
