package br.gabriel.springrestspecialist.core.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface Permission {
    @PreAuthorize("hasAuthority('READ_RESOURCE')")
    @Retention(RUNTIME)
    @Target(METHOD)
    @interface Read {
    }

    @PreAuthorize("hasAuthority('WRITE_RESOURCE')")
    @Retention(RUNTIME)
    @Target(METHOD)
    @interface Write {
    }

    @PreAuthorize("hasAuthority('DELETE_RESOURCE')")
    @Retention(RUNTIME)
    @Target(METHOD)
    @interface Delete {
    }
}
