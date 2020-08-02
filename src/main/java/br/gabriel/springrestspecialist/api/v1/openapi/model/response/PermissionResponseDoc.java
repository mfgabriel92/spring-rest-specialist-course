package br.gabriel.springrestspecialist.api.v1.openapi.model.response;

import br.gabriel.springrestspecialist.api.v1.model.response.PermissionResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.model.LinksDoc;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("PermissionsResponse")
@Getter
@Setter
public class PermissionResponseDoc {
    private Embedded _embedded;
    
    private LinksDoc _links;
    
    @Getter
    @Setter
    private static class Embedded {
        private List<PermissionResponse> groups;
    }
}
