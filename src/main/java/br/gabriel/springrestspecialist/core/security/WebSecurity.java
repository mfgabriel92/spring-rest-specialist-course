package br.gabriel.springrestspecialist.core.security;

import br.gabriel.springrestspecialist.domain.repository.OrderRepository;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class WebSecurity {
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Autowired
    private OrderRepository orderRepository;

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Long getLoggedUserId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();
        return jwt.getClaim("id");
    }
    
    public Boolean canManageRestaurant(Integer restaurantId) {
        if (restaurantId == null) {
            return false;
        }
        
        return restaurantRepository.isOwnedBy(restaurantId, getLoggedUserId().intValue());
    }
    
    public Boolean canAlterOrderStatus(String orderCode) {
        Integer restaurantId = orderRepository.findByCode(orderCode).get().getRestaurant().getId();
        return canManageRestaurant(restaurantId);
    }
}
