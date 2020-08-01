package br.gabriel.springrestspecialist.api.v1.openapi.controller;

import br.gabriel.springrestspecialist.api.v1.model.response.PaymentMethodResponse;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Restaurant")
public interface RestaurantPaymentMethodDoc {
    @ApiOperation("List all the payment methods of a restaurant")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    CollectionModel<PaymentMethodResponse> findAll(@ApiParam(value = "The restaurant ID", required = true) Integer id);

    @ApiOperation("Associate a payment method to a restaurant")
    ResponseEntity<Void> addPaymentMethod(
        @ApiParam(value = "The restaurant ID", required = true) Integer id, 
        @ApiParam(value = "The restaurant body", required = true) Integer paymentMethodId
    );

    @ApiOperation("Disassociate a payment method from a restaurant")
    ResponseEntity<Void> removePaymentMethod(
        @ApiParam(value = "The restaurant ID", required = true) Integer id, 
        @ApiParam(value = "The restaurant body", required = true) Integer paymentMethodId
    );
}
