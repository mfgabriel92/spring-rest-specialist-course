package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.request.UserPasswordRequest;
import br.gabriel.springrestspecialist.api.model.request.UserRequest;
import br.gabriel.springrestspecialist.api.model.request.UserSummaryRequest;
import br.gabriel.springrestspecialist.api.model.response.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "User")
public interface UserDoc {
    @ApiOperation("List all the users")
    List<UserResponse> findAll();

    @ApiOperation("Find a user")
    UserResponse findById(Integer id);

    @ApiOperation("Create a new user")
    UserResponse save(UserRequest userRequest);

    @ApiOperation("Update a user")
    UserResponse save(Integer id, UserSummaryRequest userRequest);

    @ApiOperation("Change the password of a user")
    void password(Integer id, UserPasswordRequest passwordRequest);

    @ApiOperation("Delete a user")
    void delete(Integer id);
}
