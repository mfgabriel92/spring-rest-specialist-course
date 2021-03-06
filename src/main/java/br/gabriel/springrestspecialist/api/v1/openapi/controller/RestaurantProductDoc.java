package br.gabriel.springrestspecialist.api.v1.openapi.controller;

import br.gabriel.springrestspecialist.api.v1.model.request.ProductRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.ProductResponse;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Restaurant")
public interface RestaurantProductDoc {
    @ApiOperation("Find all products of a restaurant")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    CollectionModel<ProductResponse> findAll(@ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id);

    @ApiOperation("Find a product of a restaurant")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    ProductResponse findById(
        @ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The product ID", example = "1", required = true) Integer productId
    );

    @ApiOperation("Create a new product for a restaurant")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    ProductResponse save(
        @ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id,
        @ApiParam(value = "The product body", required = true) ProductRequest productRequest
    );

    @ApiOperation("Update a product of a restaurant")
    ProductResponse save(
        @ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The product ID", example = "1", required = true) Integer productId, 
        @ApiParam(value = "The product body", required = true) ProductRequest productRequest
    );
}
