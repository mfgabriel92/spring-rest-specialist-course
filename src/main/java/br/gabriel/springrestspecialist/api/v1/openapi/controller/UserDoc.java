package br.gabriel.springrestspecialist.api.v1.openapi.controller;

import br.gabriel.springrestspecialist.api.v1.model.request.UserPasswordRequest;
import br.gabriel.springrestspecialist.api.v1.model.request.UserRequest;
import br.gabriel.springrestspecialist.api.v1.model.request.UserSummaryRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.UserResponse;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "User")
public interface UserDoc {
    @ApiOperation("List all the users")
    CollectionModel<UserResponse> findAll();

    @ApiOperation("Find a user")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
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
