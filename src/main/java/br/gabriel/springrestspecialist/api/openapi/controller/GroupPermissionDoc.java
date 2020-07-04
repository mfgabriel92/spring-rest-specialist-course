package br.gabriel.springrestspecialist.api.openapi.controller;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.response.PermissionResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Group permission")
public interface GroupPermissionDoc {
    @ApiOperation("List all the permissions of a group")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    List<PermissionResponse> findAll(@ApiParam(value = "The group ID", example = "1", required = true) Integer id);

    @ApiOperation("Associate a permission to a group")
    void save(
        @ApiParam(value = "The group ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The permission ID", example = "1", required = true) Integer permissionId
    );

    @ApiOperation("Disassociate a permission from a group")
    void delete(
        @ApiParam(value = "The group ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The permission ID", example = "1", required = true) Integer permissionId
    );
}
