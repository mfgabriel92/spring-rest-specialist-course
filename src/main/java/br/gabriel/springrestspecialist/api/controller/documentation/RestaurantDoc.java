package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gabriel.springrestspecialist.api.model.request.RestaurantRequest;
import br.gabriel.springrestspecialist.api.model.response.RestaurantResponse;
import br.gabriel.springrestspecialist.api.model.response.RestaurantSummaryResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Restaurant")
public interface RestaurantDoc {
    @ApiOperation("LIst all the restaurants")
    Page<RestaurantSummaryResponse> findAll(Pageable pageable);

    @ApiOperation("Find a restaurant")
    RestaurantResponse findById(Integer id);

    @ApiOperation("Create a new restaurant")
    RestaurantResponse save(RestaurantRequest restaurantRequest);

    @ApiOperation("Update a restaurant")
    RestaurantResponse save(Integer id, RestaurantRequest restaurantRequest);

    @ApiOperation("Activate a restaurant")
    void activate(Integer id);

    @ApiOperation("Activate a list of restaurants")
    void activate(List<Integer> ids);

    @ApiOperation("Deactivate a restaurant")
    void deactivate(Integer id);

    @ApiOperation("Deactivate a list of restaurant")
    void deactivate(List<Integer> ids);

    @ApiOperation("Set a restaurant as open")
    void open(Integer id);

    @ApiOperation("Set a restaurant as closed")
    void close(Integer id);
}
