package br.gabriel.springrestspecialist.api.controller.documentation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gabriel.springrestspecialist.api.model.request.OrderRequest;
import br.gabriel.springrestspecialist.api.model.response.OrderResponse;
import br.gabriel.springrestspecialist.api.model.response.OrderSummaryResponse;
import br.gabriel.springrestspecialist.domain.filter.OrderFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Order")
public interface OrderDoc {
    @ApiOperation("List all the orders")
    Page<OrderSummaryResponse> findAll(OrderFilter filter, Pageable pageable);

    @ApiOperation("Find an order")
    OrderResponse findById(@ApiParam(value = "The code of the order", example = "e84397d9-e1aa-4116-b110-f032c0a13b16", required = true) String code);

    @ApiOperation("Create a new order")
    OrderResponse save(@ApiParam(value = "The body of the order", required = true) OrderRequest orderRequest);
}
