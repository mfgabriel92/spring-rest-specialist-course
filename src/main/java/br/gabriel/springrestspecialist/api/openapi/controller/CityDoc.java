package br.gabriel.springrestspecialist.api.openapi.controller;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.request.CityRequest;
import br.gabriel.springrestspecialist.api.model.response.CityResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "City")
public interface CityDoc {
    @ApiOperation("List all cities")
    public List<CityResponse> findAll();
    
    @ApiOperation("Find a city")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    public CityResponse findById(@ApiParam(value = "The city ID", example = "1", required = true) Integer id);
    
    @ApiOperation("Create a new city")
    public CityResponse save(@ApiParam(value = "The city body", required = true) CityRequest cityRequest);

    @ApiOperation("Update a city")
    public CityResponse save(
        @ApiParam(value = "The city ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The city body", required = true) CityRequest cityRequest
    );

    @ApiOperation("Delete a city")
    public void delete(@ApiParam(value = "The city ID", example = "1", required = true) Integer id);
}
