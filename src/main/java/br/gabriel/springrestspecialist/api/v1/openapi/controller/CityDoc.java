package br.gabriel.springrestspecialist.api.v1.openapi.controller;

import br.gabriel.springrestspecialist.api.v1.model.request.CityRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.CityResponse;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "City")
public interface CityDoc {
    @ApiOperation("List all cities")
    CollectionModel<CityResponse> findAll();
    
    @ApiOperation("Find a city")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    CityResponse findById(@ApiParam(value = "The city ID", example = "1", required = true) Integer id);
    
    @ApiOperation("Create a new city")
    CityResponse save(@ApiParam(value = "The city body", required = true) CityRequest cityRequest);

    @ApiOperation("Update a city")
    CityResponse save(
        @ApiParam(value = "The city ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The city body", required = true) CityRequest cityRequest
    );

    @ApiOperation("Delete a city")
    void delete(@ApiParam(value = "The city ID", example = "1", required = true) Integer id);
}
