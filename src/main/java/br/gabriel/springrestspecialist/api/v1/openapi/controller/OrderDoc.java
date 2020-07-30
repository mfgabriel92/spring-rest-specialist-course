package br.gabriel.springrestspecialist.api.v1.openapi.controller;

import br.gabriel.springrestspecialist.api.v1.model.request.OrderRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.OrderResponse;
import br.gabriel.springrestspecialist.api.v1.model.response.OrderSummaryResponse;
import br.gabriel.springrestspecialist.domain.filter.OrderFilter;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Order")
public interface OrderDoc {
    @ApiImplicitParams({
        @ApiImplicitParam(name = "fields", value = "Fields to be filtered", paramType = "query", type = "string")
    })
    @ApiOperation("List all the orders")
    PagedModel<OrderSummaryResponse> findAll(OrderFilter filter, Pageable pageable);

    @ApiImplicitParams({
        @ApiImplicitParam(name = "fields", value = "Fields to be filtered", paramType = "query", type = "string")
    })
    @ApiOperation("Find an order")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    OrderResponse findById(@ApiParam(value = "The code of the order", example = "e84397d9-e1aa-4116-b110-f032c0a13b16", required = true) String code);

    @ApiOperation("Create a new order")
    OrderResponse save(@ApiParam(value = "The body of the order", required = true) OrderRequest orderRequest);
}
