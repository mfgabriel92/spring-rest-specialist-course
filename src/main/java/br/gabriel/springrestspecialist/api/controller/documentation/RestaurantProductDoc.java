package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.request.ProductRequest;
import br.gabriel.springrestspecialist.api.model.response.ProductResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Restaurant product")
public interface RestaurantProductDoc {
    @ApiOperation("Find all products of a restaurant")
    List<ProductResponse> findAll(@ApiParam(value = "The restaurant ID", example = "1") Integer id);

    @ApiOperation("Find a product of a restaurant")
    ProductResponse findById(
        @ApiParam(value = "The restaurant ID", example = "1") Integer id, 
        @ApiParam(value = "The product ID", example = "1") Integer productId
    );

    @ApiOperation("Create a new product for a restaurant")
    ProductResponse save(
        @ApiParam(value = "The restaurant ID", example = "1") Integer id,
        @ApiParam(value = "The product ID", example = "1") ProductRequest productRequest
    );

    @ApiOperation("Update a product of a restaurant")
    ProductResponse save(
        @ApiParam(value = "The restaurant ID", example = "1") Integer id, 
        @ApiParam(value = "The product ID", example = "1") Integer productId, 
        @ApiParam("The product body") ProductRequest productRequest
    );
}
