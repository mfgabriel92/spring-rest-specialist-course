package br.gabriel.springrestspecialist.api.controller.documentation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gabriel.springrestspecialist.api.model.request.CuisineRequest;
import br.gabriel.springrestspecialist.api.model.response.CuisineResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Cuisine")
public interface CuisineDoc {
    @ApiOperation("List all the cuisines")
    Page<CuisineResponse> findAll(Pageable pageable);

    @ApiOperation("Find a cuisine")
    CuisineResponse findById(Integer id);

    @ApiOperation("Create a new cuisine")
    CuisineResponse save(CuisineRequest cuisineRequest);

    @ApiOperation("Update a cuisine")
    CuisineResponse save(Integer id, CuisineRequest cuisineRequest);

    @ApiOperation("Delete a cuisine")
    void delete(Integer id);
}
