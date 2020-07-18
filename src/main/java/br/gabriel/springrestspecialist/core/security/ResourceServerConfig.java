package br.gabriel.springrestspecialist.core.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {
    @Value("${srs.jwt.signing-key}")
    private String signingKey;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/v1/users/**").permitAll()
                .anyRequest().authenticated().and()
            .cors().and()
            .csrf().disable()
            .oauth2ResourceServer().jwt();
    }

    @Bean
    protected JwtDecoder jwtDecoder() {
        System.out.println(signingKey);
        SecretKeySpec keySpec = new SecretKeySpec(signingKey.getBytes(), "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(keySpec).build();
    }
}
