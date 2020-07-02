package br.gabriel.springrestspecialist.api.controller.documentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Order status")
public interface OrderStatusDoc {
    @ApiOperation("Confirm an order")
    void confirm(@ApiParam(value = "The code of the order", example = "e84397d9-e1aa-4116-b110-f032c0a13b16") String code);
    
    @ApiOperation("Deliver an order")
    void deliver(@ApiParam(value = "The code of the order", example = "b2320dd2-7443-48f1-8d6c-e1946328e200") String code);

    @ApiOperation("Cancel an order")
    void cancel(@ApiParam(value = "The code of the order", example = "e84397d9-e1aa-4116-b110-f032c0a13b16") String code);
}
