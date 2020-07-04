package br.gabriel.springrestspecialist.api.openapi.controller;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.response.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurant")
public interface RestaurantUserDoc {
    @ApiOperation("List all users of a restaurant")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    List<UserResponse> findAll(@ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id);

    @ApiOperation("Associate an user to a restaurant")
    void addRestaurant(
        @ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The user ID", example = "1", required = true) Integer userId
    );

    @ApiOperation("Disassociate an user from a restaurant")
    void removeRestaurant(
        @ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The user ID", example = "1", required = true) Integer userId
    );
}
