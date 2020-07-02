package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import br.gabriel.springrestspecialist.api.model.response.PaymentMethodResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Restaurant payment method")
public interface RestaurantPaymentMethodDoc {
    @ApiOperation("List all the payment methods of a restaurant")
    List<PaymentMethodResponse> findAll(Integer id);

    @ApiOperation("Associate a payment method to a restaurant")
    void addPaymentMethod(Integer id, Integer paymentMethodId);

    @ApiOperation("Disassociate a payment method from a restaurant")
    void removePaymentMethod(Integer id, Integer paymentMethodId);
}
