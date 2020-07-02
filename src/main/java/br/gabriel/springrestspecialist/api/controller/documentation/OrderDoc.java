package br.gabriel.springrestspecialist.api.controller.documentation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gabriel.springrestspecialist.api.model.request.OrderRequest;
import br.gabriel.springrestspecialist.api.model.response.OrderResponse;
import br.gabriel.springrestspecialist.api.model.response.OrderSummaryResponse;
import br.gabriel.springrestspecialist.domain.filter.OrderFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Order")
public interface OrderDoc {
    @ApiOperation("List all the orders")
    Page<OrderSummaryResponse> findAll(OrderFilter filter, Pageable pageable);

    @ApiOperation("Find an order")
    OrderResponse findById(String code);

    @ApiOperation("Create a new order")
    OrderResponse save(OrderRequest orderRequest);
}
