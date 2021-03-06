package br.gabriel.springrestspecialist.core.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider;
import com.github.bohnman.squiggly.web.SquigglyRequestFilter;

@Configuration
public class SquigglyConfig {
    @Bean
    public FilterRegistrationBean<SquigglyRequestFilter> filterRegistrationBean(ObjectMapper objectMapper) {
        Squiggly.init(objectMapper, new RequestSquigglyContextProvider());
        
        FilterRegistrationBean<SquigglyRequestFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new SquigglyRequestFilter());
        filterRegistration.setOrder(1);
        filterRegistration.setUrlPatterns(urlPatterns());
        
        return filterRegistration;
    }
    
    private List<String> urlPatterns() {
        return Arrays.asList("/orders/*", "/restaurants/*");
    }
}
 