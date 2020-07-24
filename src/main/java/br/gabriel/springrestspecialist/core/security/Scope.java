package br.gabriel.springrestspecialist.core.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface Scope {
    @PreAuthorize("hasAuthority('SCOPE_READ')")
    @Retention(RUNTIME)
    @Target(METHOD)
    @interface Read {
    }

    @PreAuthorize("hasAuthority('SCOPE_WRITE')")
    @Retention(RUNTIME)
    @Target(METHOD)
    @interface Write {
    }

    @PreAuthorize("hasAuthority('SCOPE_DELETE')")
    @Retention(RUNTIME)
    @Target(METHOD)
    @interface Delete {
    }
}
