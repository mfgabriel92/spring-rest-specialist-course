package br.gabriel.springrestspecialist.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gabriel.springrestspecialist.api.model.request.OrderRequest;
import br.gabriel.springrestspecialist.api.model.response.OrderResponse;
import br.gabriel.springrestspecialist.api.model.response.OrderSummaryResponse;
import br.gabriel.springrestspecialist.domain.filter.OrderFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Order")
public interface OrderDoc {
    @ApiImplicitParams({
        @ApiImplicitParam(name = "fields", value = "Fields to be filtered", paramType = "query", type = "string")
    })
    @ApiOperation("List all the orders")
    Page<OrderSummaryResponse> findAll(OrderFilter filter, Pageable pageable);

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
