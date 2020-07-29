package br.gabriel.springrestspecialist.api.v1.openapi.controller;

import br.gabriel.springrestspecialist.api.v1.model.response.UserResponse;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Restaurant")
public interface RestaurantUserDoc {
    @ApiOperation("List all users of a restaurant")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    CollectionModel<UserResponse> findAll(@ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id);

    @ApiOperation("Associate an user to a restaurant")
    void assignToRestaurant(
        @ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The user ID", example = "1", required = true) Integer userId
    );

    @ApiOperation("Disassociate an user from a restaurant")
    void unassignFromRestaurant(
        @ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The user ID", example = "1", required = true) Integer userId
    );
}
