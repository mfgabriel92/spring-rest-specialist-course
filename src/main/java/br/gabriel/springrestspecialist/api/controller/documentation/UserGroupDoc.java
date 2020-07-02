package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.response.GroupResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "User group")
public interface UserGroupDoc {
    @ApiOperation("List all the groups of a user")
    List<GroupResponse> findAll(Integer id);

    @ApiOperation("Associate a group to a user")
    void save(Integer id, Integer groupId);

    @ApiOperation("Disassociate a group from a user")
    void delete(Integer id, Integer groupId);
}
