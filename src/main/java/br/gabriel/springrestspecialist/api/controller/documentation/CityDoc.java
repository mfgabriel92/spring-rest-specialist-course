package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.request.CityRequest;
import br.gabriel.springrestspecialist.api.model.response.CityResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "City")
public interface CityDoc {
    @ApiOperation("List all cities")
    public List<CityResponse> findAll();
    
    @ApiOperation("Find a city")
    public CityResponse findById(@ApiParam(value = "The city ID", example = "1") Integer id);
    
    @ApiOperation("Create a new city")
    public CityResponse save(@ApiParam("The city body") CityRequest cityRequest);

    @ApiOperation("Update a city")
    public CityResponse save(
        @ApiParam(value = "The city ID", example = "1") Integer id, 
        @ApiParam("The city body") CityRequest cityRequest
    );

    @ApiOperation("Delete a city")
    public void delete(@ApiParam(value = "The city ID", example = "1") Integer id);
}
