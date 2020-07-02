package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.gabriel.springrestspecialist.api.model.request.CityRequest;
import br.gabriel.springrestspecialist.api.model.response.CityResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "City")
public interface CityDoc {
    @ApiOperation("List all the cities")
    public List<CityResponse> findAll();
    
    @ApiOperation("Find one city")
    public CityResponse findById(@PathVariable Integer id);
    
    @ApiOperation("Create a new city")
    public CityResponse save(@RequestBody @Valid CityRequest cityRequest);

    @ApiOperation("Update a new city")
    public CityResponse save(@PathVariable Integer id, @RequestBody @Valid CityRequest cityRequest);

    @ApiOperation("Delete a city")
    public void delete(@PathVariable Integer id);
}
