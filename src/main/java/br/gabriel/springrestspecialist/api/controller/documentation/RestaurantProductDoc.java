package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.request.ProductRequest;
import br.gabriel.springrestspecialist.api.model.response.ProductResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Restaurant product")
public interface RestaurantProductDoc {
    @ApiOperation("Find all products of a restaurant")
    List<ProductResponse> findAll(Integer id);

    @ApiOperation("Find a product of a restaurant")
    ProductResponse findById(Integer id, Integer productId);

    @ApiOperation("Create a new product for a restaurant")
    ProductResponse save(Integer id, ProductRequest productRequest);

    @ApiOperation("Update a product of a restaurant")
    ProductResponse save(Integer id, Integer productId, ProductRequest productRequest);
}
