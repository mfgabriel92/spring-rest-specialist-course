package br.gabriel.springrestspecialist.api.v1.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import br.gabriel.springrestspecialist.domain.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {
    private String code;

    private BigDecimal shippingFee;
    
    private BigDecimal subtotal;
    
    private BigDecimal grandTotal;
    
    private OffsetDateTime confirmedAt;
    
    private OffsetDateTime canceledAt;
    
    private OffsetDateTime deliveredAt;
    
    private AddressResponse deliveryAddress;
    
    private PaymentMethodResponse paymentMethod;
    
    private RestaurantResponse restaurant;
    
    private UserResponse user;
    
    private OrderStatus status;
    
    private OffsetDateTime createdAt;
    
    private List<OrderItemResponse> items;
}
