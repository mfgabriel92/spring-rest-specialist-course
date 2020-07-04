package br.gabriel.springrestspecialist.api.v1.openapi.controller;

import java.util.List;

import br.gabriel.springrestspecialist.api.v1.model.request.GroupRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.GroupResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Group")
public interface GroupDoc {
    @ApiOperation("List all the groups")
    List<GroupResponse> findAll();

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