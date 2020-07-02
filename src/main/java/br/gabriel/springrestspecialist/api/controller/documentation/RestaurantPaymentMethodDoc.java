package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.response.PaymentMethodResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Restaurant payment method")
public interface RestaurantPaymentMethodDoc {
    @ApiOperation("List all the payment methods of a restaurant")
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
