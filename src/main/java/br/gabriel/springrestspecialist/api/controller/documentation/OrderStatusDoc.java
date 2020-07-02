package br.gabriel.springrestspecialist.api.controller.documentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Order status")
public interface OrderStatusDoc {
    @ApiOperation("Confirm an order")
    void confirm(String code);
    
    @ApiOperation("Deliver an order")
    void deliver(String code);

    @ApiOperation("Cancel an order")
    void cancel(String code);
}
