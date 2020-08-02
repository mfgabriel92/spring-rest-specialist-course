package br.gabriel.springrestspecialist.api.v1.openapi.controller;

import br.gabriel.springrestspecialist.api.v1.model.request.PaymentMethodRequest;
import br.gabriel.springrestspecialist.api.v1.model.response.PaymentMethodResponse;
import br.gabriel.springrestspecialist.api.v1.openapi.model.response.PaymentMethodResponseDoc;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Payment method")
public interface PaymentMethodDoc {
    @ApiOperation(value = "List all the payment methods", response = PaymentMethodResponseDoc.class)
    ResponseEntity<CollectionModel<PaymentMethodResponse>> findAll();

    @ApiOperation("Find a payment method")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    ResponseEntity<PaymentMethodResponse> findById(@ApiParam(value = "The payment method ID", example = "1", required = true) Integer id);

    @ApiOperation("Create a new payment method")
    PaymentMethodResponse save(@ApiParam(value = "The payment method body", required = true) PaymentMethodRequest paymentMethodRequest);

    @ApiOperation("Update a payment method")
    PaymentMethodResponse save(
        @ApiParam(value = "The payment method ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The payment method body", required = true) PaymentMethodRequest paymentMethodRequest
    );

    @ApiOperation("Delete a payment method")
    void delete(@ApiParam(value = "The payment method ID", example = "1", required = true) Integer id);
}
