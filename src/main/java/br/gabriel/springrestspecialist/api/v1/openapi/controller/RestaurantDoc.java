package br.gabriel.springrestspecialist.api.v1.openapi.controller;

import br.gabriel.springrestspecialist.api.v1.model.request.RestaurantRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.RestaurantResponse;
import br.gabriel.springrestspecialist.api.v1.model.response.RestaurantSummaryResponse;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Restaurant")
public interface RestaurantDoc {
    @ApiIgnore
    @ApiOperation("LIst all the restaurants")
    PagedModel<RestaurantSummaryResponse> findAll(Pageable pageable);

    @ApiOperation("Find a restaurant")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    RestaurantResponse findById(@ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id);

    @ApiOperation("Create a new restaurant")
    RestaurantResponse save(@ApiParam(value = "The restaurant body", required = true) RestaurantRequest restaurantRequest);

    @ApiOperation("Update a restaurant")
    RestaurantResponse save(
        @ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The restaurant body", required = true) RestaurantRequest restaurantRequest
    );

    @ApiOperation("Activate a list of restaurants")
    ResponseEntity<Void> activate(@ApiParam(value = "The list of restaurants ID", example = "[1, 2]", required = true) Integer id);

    @ApiOperation("Deactivate a list of restaurant")
    ResponseEntity<Void> deactivate(@ApiParam(value = "The list of restaurants ID", example = "[1, 2]", required = true) Integer id);

    @ApiOperation("Set a restaurant as open")
    ResponseEntity<Void> open(@ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id);

    @ApiOperation("Set a restaurant as closed")
    ResponseEntity<Void> close(@ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id);
}
