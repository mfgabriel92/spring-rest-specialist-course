package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gabriel.springrestspecialist.api.model.request.RestaurantRequest;
import br.gabriel.springrestspecialist.api.model.response.RestaurantResponse;
import br.gabriel.springrestspecialist.api.model.response.RestaurantSummaryResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Restaurant")
public interface RestaurantDoc {
    @ApiOperation("LIst all the restaurants")
    Page<RestaurantSummaryResponse> findAll(Pageable pageable);

    @ApiOperation("Find a restaurant")
    RestaurantResponse findById(@ApiParam(value = "The restaurant ID", example = "1") Integer id);

    @ApiOperation("Create a new restaurant")
    RestaurantResponse save(RestaurantRequest restaurantRequest);

    @ApiOperation("Update a restaurant")
    RestaurantResponse save(
        @ApiParam(value = "The restaurant ID", example = "1") Integer id, 
        @ApiParam("The restaurant body") RestaurantRequest restaurantRequest
    );

    @ApiOperation("Activate a restaurant")
    void activate(@ApiParam(value = "The restaurant ID", example = "1") Integer id);

    @ApiOperation("Activate a list of restaurants")
    void activate(@ApiParam(value = "The list of restaurants ID", example = "[1, 2]") List<Integer> ids);

    @ApiOperation("Deactivate a restaurant")
    void deactivate(@ApiParam(value = "The restaurant ID", example = "1") Integer id);

    @ApiOperation("Deactivate a list of restaurant")
    void deactivate(@ApiParam(value = "The list of restaurants ID", example = "[1, 2]") List<Integer> ids);

    @ApiOperation("Set a restaurant as open")
    void open(@ApiParam(value = "The restaurant ID", example = "1") Integer id);

    @ApiOperation("Set a restaurant as closed")
    void close(@ApiParam(value = "The restaurant ID", example = "1") Integer id);
}
