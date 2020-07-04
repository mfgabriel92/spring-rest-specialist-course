package br.gabriel.springrestspecialist.api.openapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Order")
public interface OrderStatusDoc {
    @ApiOperation("Confirm an order")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    void confirm(@ApiParam(value = "The code of the order", example = "e84397d9-e1aa-4116-b110-f032c0a13b16", required = true) String code);
    
    @ApiOperation("Deliver an order")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    void deliver(@ApiParam(value = "The code of the order", example = "b2320dd2-7443-48f1-8d6c-e1946328e200", required = true) String code);

    @ApiOperation("Cancel an order")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    void cancel(@ApiParam(value = "The code of the order", example = "e84397d9-e1aa-4116-b110-f032c0a13b16", required = true) String code);
}
