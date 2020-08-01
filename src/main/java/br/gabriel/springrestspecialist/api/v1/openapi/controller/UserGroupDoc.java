package br.gabriel.springrestspecialist.api.v1.openapi.controller;

import br.gabriel.springrestspecialist.api.v1.model.response.GroupResponse;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "User")
public interface UserGroupDoc {
    @ApiOperation("List all the groups of a user")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    CollectionModel<GroupResponse> findAll(@ApiParam(value = "The user ID", example = "1", required = true) Integer id);

    @ApiOperation("Associate a group to a user")
    ResponseEntity<Void> associate(
        @ApiParam(value = "The user ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The group ID", example = "1", required = true) Integer groupId
    );

    @ApiOperation("Disassociate a group from a user")
    ResponseEntity<Void> disassociate(
        @ApiParam(value = "The user ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The group ID", example = "1", required = true) Integer groupId
    );
}
