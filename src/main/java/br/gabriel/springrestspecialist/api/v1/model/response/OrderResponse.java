package br.gabriel.springrestspecialist.api.v1.model.response;

import br.gabriel.springrestspecialist.domain.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Relation(collectionRelation = "orders")
@Getter
@Setter
public class OrderResponse extends RepresentationModel<OrderResponse> {
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
