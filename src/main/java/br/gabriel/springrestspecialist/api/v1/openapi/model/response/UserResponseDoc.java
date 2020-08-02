package br.gabriel.springrestspecialist.api.v1.openapi.model.response;

import br.gabriel.springrestspecialist.api.v1.model.response.UserResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.model.LinksDoc;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("UsersResponse")
@Getter
@Setter
public class UserResponseDoc {
    private Embedded _embedded;
    
    private LinksDoc _links;
    
    @Getter
    @Setter
    private static class Embedded {
        @ApiModelProperty("users")
        private List<UserResponse> users;
    }
}
