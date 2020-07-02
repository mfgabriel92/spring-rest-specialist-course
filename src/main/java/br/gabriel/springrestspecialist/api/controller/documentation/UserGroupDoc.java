package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.response.GroupResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "User group")
public interface UserGroupDoc {
    @ApiOperation("List all the groups of a user")
    List<GroupResponse> findAll(@ApiParam(value = "The user ID", example = "1") Integer id);

    @ApiOperation("Associate a group to a user")
    void save(
        @ApiParam(value = "The user ID", example = "1") Integer id, 
        @ApiParam(value = "The group ID", example = "1") Integer groupId
    );

    @ApiOperation("Disassociate a group from a user")
    void delete(
        @ApiParam(value = "The user ID", example = "1") Integer id, 
        @ApiParam(value = "The group ID", example = "1") Integer groupId
    );
}
