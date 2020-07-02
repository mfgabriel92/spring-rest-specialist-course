package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.gabriel.springrestspecialist.api.model.request.PaymentMethodRequest;
import br.gabriel.springrestspecialist.api.model.response.PaymentMethodResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Payment method")
public interface PaymentMethodDoc {
    @ApiOperation("List all the payment methods")
    ResponseEntity<List<PaymentMethodResponse>> findAll();

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
