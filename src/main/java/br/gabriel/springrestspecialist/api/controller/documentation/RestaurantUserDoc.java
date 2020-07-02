package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.response.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Restaurant user")
public interface RestaurantUserDoc {
    @ApiOperation("List all users of a restaurant")
    List<UserResponse> findAll(Integer id);

    @ApiOperation("Associate an user to a restaurant")
    void addRestaurant(Integer id, Integer userId);

    @ApiOperation("Disassociate an user from a restaurant")
    void removeRestaurant(Integer id, Integer userId);
}
