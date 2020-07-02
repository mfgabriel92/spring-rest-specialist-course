package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.request.GroupRequest;
import br.gabriel.springrestspecialist.api.model.response.GroupResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Group")
public interface GroupDoc {
    @ApiOperation("List all the groups")
    List<GroupResponse> findAll();

    @ApiOperation("Find a group")
    GroupResponse findById(Integer id);

    @ApiOperation("Create a new group")
    GroupResponse save(GroupRequest groupRequest);

    @ApiOperation("Update a group")
    GroupResponse save(Integer id, GroupRequest groupRequest);

    @ApiOperation("Delete a group")
    void delete(Integer id);
}