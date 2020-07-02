package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.request.GroupRequest;
import br.gabriel.springrestspecialist.api.model.response.GroupResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Group")
public interface GroupDoc {
    @ApiOperation("List all the groups")
    List<GroupResponse> findAll();

    @ApiOperation("Find a group")
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