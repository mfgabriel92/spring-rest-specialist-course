package br.gabriel.springrestspecialist.core.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class OpenApiConfig implements WebMvcConfigurer {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
                .apis(RequestHandlerSelectors.basePackage("br.gabriel.springrestspecialist.api"))
                .build()
            .apiInfo(apiInfo())
            .tags(tags()[0], tags());
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");
        
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Spring Rest Specialist")
            .description("Spring Rest Specialist course API")
            .version("0.0.1")
            .build();
    }
    
    private Tag[] tags() {
        return Arrays.asList(
            new Tag("City", "Manage the cities"),
            new Tag("Cuisine", "Manage the cuisines"),
            new Tag("Group", "Manage the groups"),
            new Tag("Group permission", "Manage the groups permissions"),
            new Tag("Order", "Manage the orders"),
            new Tag("Order status", "Manage the orders status"),
            new Tag("Payment method", "Manage the payment methods"),
            new Tag("Product photo", "Manage the products photos"),
            new Tag("Restaurant", "Manage the restaurants"),
            new Tag("Restaurant payment method", "Manage the restaurants payment methods"),
            new Tag("Restaurant product", "Manage the restaurants products"),
            new Tag("Restaurant user", "Manage the restaurants owners"),
            new Tag("State", "Manage the states"),
            new Tag("Statistic", "Manage the statistics"),
            new Tag("User", "Manage the users"),
            new Tag("User group", "Manage the users groups")
        ).toArray(new Tag[0]);
    }
}
