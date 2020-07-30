package br.gabriel.springrestspecialist.api.v1.openapi.controller;

import br.gabriel.springrestspecialist.api.v1.model.request.StateRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.StateResponse;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "State")
public interface StateDoc {
    @ApiOperation("List all states")
    CollectionModel<StateResponse> findAll();
    
    @ApiOperation("Find a state")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    StateResponse findById(@ApiParam(value = "The state ID", example = "1", required = true) Integer id);
    
    @ApiOperation("Create a new state")
    StateResponse save(@ApiParam(value = "The state body", required = true) StateRequest stateRequest);

    @ApiOperation("Update a state")
    StateResponse save(
        @ApiParam(value = "The state ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The state body", required = true) StateRequest stateRequest
    );

    @ApiOperation("Delete a state")
    void delete(@ApiParam(value = "The state ID", example = "1", required = true) Integer id);
}
