package br.gabriel.springrestspecialist.core.security;

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

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Long getLoggedUserId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();
        return jwt.getClaim("id");
    }

    public Boolean canManageRestaurant(Integer restaurantId) {
        return restaurantRepository.isOwnedBy(restaurantId, getLoggedUserId().intValue());
    }
}
