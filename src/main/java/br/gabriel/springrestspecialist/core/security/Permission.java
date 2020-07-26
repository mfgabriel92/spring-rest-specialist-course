package br.gabriel.springrestspecialist.core.security;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface Permission {
    @PreAuthorize("isAuthenticated()")
    @Retention(RUNTIME)
    @Target(METHOD)
    @interface Authenticated {}
    
    @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('READ_RESOURCE')")
    @Retention(RUNTIME)
    @Target(METHOD)
    @interface Read {}
    
    @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('WRITE_RESOURCE')")
    @Retention(RUNTIME)
    @Target(METHOD)
    @interface Write {}
    
    @PreAuthorize("hasAuthority('SCOPE_DELETE') and hasAuthority('DELETE_RESOURCE')")
    @Retention(RUNTIME)
    @Target(METHOD)
    @interface Delete {}
    
    @interface Restaurant {
        @PreAuthorize(
            "hasAuthority('SCOPE_WRITE') and hasAuthority('WRITE_RESOURCE') or" +
            "@webSecurity.belongsToRestaurant(#id)"
        )
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanAlterStatus {}
    }
    
    @interface Order {
        @PreAuthorize("hasAuthority('SCOPE_READ')")
        @PostAuthorize(
            "hasAuthority('READ_RESOURCE') or" +
            "@webSecurity.getLoggedUserId() == returnObject.user.id or" +
            "@webSecurity.belongsToRestaurant(returnObject.restaurant.id)"
        )
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanRead {}
        
        @PreAuthorize(
            "hasAuthority('SCOPE_READ') and hasAuthority('READ_RESOURCE') or" +
            "@webSecurity.getLoggedUserId().intValue() == #filter.userId or" +
            "@webSecurity.belongsToRestaurant(#filter.restaurantId)"
        )
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanReadAll {}
        
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and @webSecurity.belongsToRestaurant(#code)")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanAlterStatus {}
    }
    
    @interface City {
        @PreAuthorize("hasAuthority('SCOPE_READ')")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanRead {}
    }
    
    @interface State {
        @PreAuthorize("hasAuthority('SCOPE_READ')")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanRead {}
    }
    
    @interface User {
        @PreAuthorize("hasAuthority('SCOPE_READ') and @webSecurity.getLoggedUserId() == #id")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanChangePassword {}
    
        @PreAuthorize(
            "hasAuthority('SCOPE_WRITE') and hasAuthority('WRITE_RESOURCE') or" +
            "@webSecurity.getLoggedUserId() == #id"
        )
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanWrite {}
    
        @PreAuthorize("hasAuthority('SCOPE_READ') and @webSecurity.getLoggedUserId() == #id")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanReadSelf {}
    }
}
