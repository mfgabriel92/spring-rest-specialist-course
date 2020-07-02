package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.response.PaymentMethodResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurant payment method")
public interface RestaurantPaymentMethodDoc {
    @ApiOperation("List all the payment methods of a restaurant")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    List<PaymentMethodResponse> findAll(@ApiParam(value = "The restaurant ID", required = true) Integer id);

    @ApiOperation("Associate a payment method to a restaurant")
    void addPaymentMethod(
        @ApiParam(value = "The restaurant ID", required = true) Integer id, 
        @ApiParam(value = "The restaurant body", required = true) Integer paymentMethodId
    );

    @ApiOperation("Disassociate a payment method from a restaurant")
    void removePaymentMethod(
        @ApiParam(value = "The restaurant ID", required = true) Integer id, 
        @ApiParam(value = "The restaurant body", required = true) Integer paymentMethodId
    );
}
