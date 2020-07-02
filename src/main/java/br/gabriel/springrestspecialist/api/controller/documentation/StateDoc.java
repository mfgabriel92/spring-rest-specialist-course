package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.request.StateRequest;
import br.gabriel.springrestspecialist.api.model.response.StateResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "State")
public interface StateDoc {
    @ApiOperation("List all states")
    public List<StateResponse> findAll();
    
    @ApiOperation("Find a state")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    public StateResponse findById(@ApiParam(value = "The state ID", example = "1", required = true) Integer id);
    
    @ApiOperation("Create a new state")
    public StateResponse save(@ApiParam(value = "The state body", required = true) StateRequest stateRequest);

    @ApiOperation("Update a state")
    public StateResponse save(
        @ApiParam(value = "The state ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The state body", required = true) StateRequest stateRequest
    );

    @ApiOperation("Delete a state")
    public void delete(@ApiParam(value = "The state ID", example = "1", required = true) Integer id);
}
