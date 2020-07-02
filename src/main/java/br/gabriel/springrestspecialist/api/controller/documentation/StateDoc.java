package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.request.StateRequest;
import br.gabriel.springrestspecialist.api.model.response.StateResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "State")
public interface StateDoc {
    @ApiOperation("List all states")
    List<StateResponse> findAll();

    @ApiOperation("Find a state")
    StateResponse findById(Integer id);

    @ApiOperation("Create a new state")
    StateResponse save(StateRequest stateRequest);

    @ApiOperation("Update a state")
    StateResponse save(Integer id, StateRequest stateRequest);

    @ApiOperation("Delete a state")
    void delete(Integer id);
}
