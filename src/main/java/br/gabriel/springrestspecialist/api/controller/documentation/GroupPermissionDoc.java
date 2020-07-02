package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.response.PermissionResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Group permission")
public interface GroupPermissionDoc {
    @ApiOperation("List all the permissions of a group")
    List<PermissionResponse> findAll(@ApiParam(value = "The group ID", example = "1") Integer id);

    @ApiOperation("Associate a permission to a group")
    void save(
        @ApiParam(value = "The group ID", example = "1") Integer id, 
        @ApiParam(value = "The permission ID", example = "1") Integer permissionId
    );

    @ApiOperation("Disassociate a permission from a group")
    void delete(
        @ApiParam(value = "The group ID", example = "1") Integer id, 
        @ApiParam(value = "The permission ID", example = "1") Integer permissionId
    );
}
