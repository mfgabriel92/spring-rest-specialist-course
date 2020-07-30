package br.gabriel.springrestspecialist.api.v1.openapi.controller;

import br.gabriel.springrestspecialist.api.v1.model.request.CuisineRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.CuisineResponse;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Cuisine")
public interface CuisineDoc {
    @ApiOperation("List all the cuisines")
    PagedModel<CuisineResponse> findAll(Pageable pageable);

    @ApiOperation("Find a cuisine")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    CuisineResponse findById(@ApiParam(value = "The cuisine ID", example = "1", required = true) Integer id);

    @ApiOperation("Create a new cuisine")
    CuisineResponse save(@ApiParam(value = "The cuisine body", required = true) CuisineRequest cuisineRequest);

    @ApiOperation("Update a cuisine")
    CuisineResponse save(
        @ApiParam(value = "The cuisine ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The cuisine body", required = true) CuisineRequest cuisineRequest
    );

    @ApiOperation("Delete a cuisine")
    void delete(@ApiParam(value = "The cuisine ID", example = "1", required = true) Integer id);
}
