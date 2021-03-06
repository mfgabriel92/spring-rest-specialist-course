package br.gabriel.springrestspecialist.api.v1.openapi.controller;

import br.gabriel.springrestspecialist.api.v1.model.request.GroupRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.GroupResponse;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Group")
public interface GroupDoc {
    @ApiOperation("List all the groups")
    CollectionModel<GroupResponse> findAll();

    @ApiOperation("Find a group")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    GroupResponse findById(
        @ApiParam(value = "The group ID", example = "1", required = true) Integer id
    );

    @ApiOperation("Create a new group")
    GroupResponse save(@ApiParam(value = "The group body", required = true) GroupRequest groupRequest);

    @ApiOperation("Update a group")
    GroupResponse save(
        @ApiParam(value = "The group ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The group body", required = true) GroupRequest groupRequest
    );

    @ApiOperation("Delete a group")
    void delete(@ApiParam(value = "The group ID", example = "1", required = true) Integer id);
}
