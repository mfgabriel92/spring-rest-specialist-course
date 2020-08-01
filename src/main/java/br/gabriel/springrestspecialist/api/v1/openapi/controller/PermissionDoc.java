package br.gabriel.springrestspecialist.api.v1.openapi.controller;

import br.gabriel.springrestspecialist.api.v1.model.response.PermissionResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Permission")
public interface PermissionDoc {
    @ApiOperation("List all the permissions")
    CollectionModel<PermissionResponse> findAll();
}
