package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.request.UserPasswordRequest;
import br.gabriel.springrestspecialist.api.model.request.UserRequest;
import br.gabriel.springrestspecialist.api.model.request.UserSummaryRequest;
import br.gabriel.springrestspecialist.api.model.response.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "User")
public interface UserDoc {
    @ApiOperation("List all the users")
    List<UserResponse> findAll();

    @ApiOperation("Find a user")
    UserResponse findById(@ApiParam(value = "The user ID", example = "1", required = true) Integer id);

    @ApiOperation("Create a new user")
    UserResponse save(@ApiParam(value = "The user body", required = true) UserRequest userRequest);

    @ApiOperation("Update a user")
    UserResponse save(
        @ApiParam(value = "The user ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The user body", required = true) UserSummaryRequest userRequest
    );
    
    @ApiOperation("changes a user password")
    void password(
        @ApiParam(value = "The user ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The user body", required = true) UserPasswordRequest userRequest
    );

    @ApiOperation("Delete a user")
    void delete(@ApiParam(value = "The user ID", example = "1", required = true) Integer id);
}
