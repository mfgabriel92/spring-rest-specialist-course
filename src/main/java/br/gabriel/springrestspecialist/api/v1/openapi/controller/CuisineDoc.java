package br.gabriel.springrestspecialist.api.v1.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gabriel.springrestspecialist.api.v1.model.request.CuisineRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.CuisineResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cuisine")
public interface CuisineDoc {
    @ApiOperation("List all the cuisines")
    Page<CuisineResponse> findAll(Pageable pageable);

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
