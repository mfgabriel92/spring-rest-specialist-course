package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.response.PermissionResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Group permission")
public interface GroupPermissionDoc {
    @ApiOperation("List all the permissions of a group")
    List<PermissionResponse> findAll(Integer id);

    @ApiOperation("Associate a permission to a group")
    void save(Integer id, Integer permissionId);

    @ApiOperation("Disassociate a permission from a group")
    void delete(Integer id, Integer permissionId);
}
