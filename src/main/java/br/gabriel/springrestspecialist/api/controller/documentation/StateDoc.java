package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.request.StateRequest;
import br.gabriel.springrestspecialist.api.model.response.StateResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "State")
public interface StateDoc {
    @ApiOperation("List all states")
    public List<StateResponse> findAll();
    
    @ApiOperation("Find a state")
    public StateResponse findById(@ApiParam(value = "The state ID", example = "1") Integer id);
    
    @ApiOperation("Create a new state")
    public StateResponse save(@ApiParam("The state body") StateRequest stateRequest);

    @ApiOperation("Update a state")
    public StateResponse save(
        @ApiParam(value = "The state ID", example = "1") Integer id, 
        @ApiParam("The state body") StateRequest stateRequest
    );

    @ApiOperation("Delete a state")
    public void delete(@ApiParam(value = "The state ID", example = "1") Integer id);
}
