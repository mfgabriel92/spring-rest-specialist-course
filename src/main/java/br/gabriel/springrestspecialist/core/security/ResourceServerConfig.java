package br.gabriel.springrestspecialist.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/v1/**").hasAuthority("READ")
                .antMatchers(HttpMethod.POST, "/v1/**").hasAuthority("WRITE")
                .antMatchers(HttpMethod.PUT, "/v1/**").hasAuthority("WRITE")
                .antMatchers(HttpMethod.DELETE, "/v1/**").hasAuthority("DELETE")
                .antMatchers(HttpMethod.POST, "/v1/users/**").permitAll()
                .anyRequest().authenticated().and()
            .cors().and()
            .csrf().disable()
            .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            List<String> authorities = jwt.getClaimAsStringList("authorities");

            if (authorities == null) {
                authorities = Collections.emptyList();
            }

            return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        });

        return jwtAuthenticationConverter;
    }
}
