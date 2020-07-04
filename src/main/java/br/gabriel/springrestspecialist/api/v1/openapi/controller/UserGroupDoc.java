package br.gabriel.springrestspecialist.api.v1.openapi.controller;

import java.util.List;

import br.gabriel.springrestspecialist.api.v1.model.response.GroupResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "User")
public interface UserGroupDoc {
    @ApiOperation("List all the groups of a user")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    List<GroupResponse> findAll(@ApiParam(value = "The user ID", example = "1", required = true) Integer id);

    @ApiOperation("Associate a group to a user")
    void save(
        @ApiParam(value = "The user ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The group ID", example = "1", required = true) Integer groupId
    );

    @ApiOperation("Disassociate a group from a user")
    void delete(
        @ApiParam(value = "The user ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The group ID", example = "1", required = true) Integer groupId
    );
}
