package br.gabriel.springrestspecialist.core.config;

import br.gabriel.springrestspecialist.api.exception.ExceptionMessage;
import br.gabriel.springrestspecialist.api.v1.model.response.*;
import br.gabriel.springrestspecialist.api.v1.openapi.model.LinksDoc;
import br.gabriel.springrestspecialist.api.v1.openapi.model.PageableDoc;
import br.gabriel.springrestspecialist.api.v1.openapi.model.response.*;
import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class OpenApiConfig implements WebMvcConfigurer {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
                .apis(RequestHandlerSelectors.basePackage("br.gabriel.springrestspecialist.api.v1"))
                .paths(PathSelectors.any())
                .build()
            .apiInfo(apiInfo())
            .tags(tags()[0], tags())
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
            .globalResponseMessage(RequestMethod.POST, globalPostResponseMessages())
            .globalResponseMessage(RequestMethod.PUT, globalPutResponseMessages())
            .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
            .additionalModels(additionalModels()[0], additionalModels())
            .directModelSubstitute(Pageable.class, PageableDoc.class)
            .directModelSubstitute(Links.class, LinksDoc.class)
            .alternateTypeRules(alternateTypeRules())
            .ignoredParameterTypes(ignoredParameterTypes())
            .securitySchemes(Collections.singletonList(securityScheme()))
            .securityContexts(Collections.singletonList(securityContext()));
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
            .version("1")
            .build();
    }
    
    private Tag[] tags() {
        return Arrays.asList(
            new Tag("City", "Manage the cities"),
            new Tag("Cuisine", "Manage the cuisines"),
            new Tag("Group", "Manage the groups"),
            new Tag("Order", "Manage the orders"),
            new Tag("Payment method", "Manage the payment methods"),
            new Tag("Restaurant", "Manage the restaurants"),
            new Tag("State", "Manage the states"),
            new Tag("Statistic", "Manage the statistics"),
            new Tag("User", "Manage the users"),
            new Tag("User", "Manage the users groups"),
            new Tag("Permission", "Manage the permissions"),
            new Tag("Root", "Manage the root entry points")
        ).toArray(new Tag[0]);
    }

    private List<ResponseMessage> globalGetResponseMessages() {
        return Arrays.asList(
            responseMessage(HttpStatus.INTERNAL_SERVER_ERROR),
            responseMessage(HttpStatus.NOT_ACCEPTABLE)
        );
    }
    
    private List<ResponseMessage> globalPostResponseMessages() {
        return Arrays.asList(
            responseMessage(HttpStatus.INTERNAL_SERVER_ERROR),
            responseMessage(HttpStatus.BAD_REQUEST),
            responseMessage(HttpStatus.NOT_ACCEPTABLE),
            responseMessage(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
        );
    }
    
    private List<ResponseMessage> globalPutResponseMessages() {
        List<ResponseMessage> put = new ArrayList<>(globalPostResponseMessages());
        put.add(responseMessage(HttpStatus.NOT_FOUND));
        
        return put;
    }
    
    private List<ResponseMessage> globalDeleteResponseMessages() {
        return Arrays.asList(
            responseMessage(HttpStatus.INTERNAL_SERVER_ERROR),
            responseMessage(HttpStatus.BAD_REQUEST)
        );
    }
    
    private ResponseMessage responseMessage(HttpStatus status) {
        return new ResponseMessageBuilder().code(status.value()).message(status.getReasonPhrase()).build();
    }
    
    private ResolvedType[] additionalModels() {
        return Collections.singletonList(
            new TypeResolver().resolve(ExceptionMessage.class)
        ).toArray(new ResolvedType[0]);
    }
    
    private AlternateTypeRule[] alternateTypeRules() {
        TypeResolver typeResolver = new TypeResolver();
        
        return Arrays.asList(
            AlternateTypeRules.newRule(
                typeResolver.resolve(PagedModel.class, CuisineResponse.class),
                CuisineResponseDoc.class
            ),
            AlternateTypeRules.newRule(
                typeResolver.resolve(PagedModel.class, OrderSummaryResponse.class),
                OrderSummaryResponseDoc.class
            ),
            AlternateTypeRules.newRule(
                typeResolver.resolve(CollectionModel.class, CityResponse.class),
                CityResponseDoc.class
            ),
            AlternateTypeRules.newRule(
                typeResolver.resolve(CollectionModel.class, StateResponse.class),
                StateResponseDoc.class
            ),
            AlternateTypeRules.newRule(
                typeResolver.resolve(CollectionModel.class, PaymentMethodResponse.class),
                PaymentMethodResponseDoc.class
            ),
            AlternateTypeRules.newRule(
                typeResolver.resolve(CollectionModel.class, GroupResponse.class),
                GroupResponseDoc.class
            ),
            AlternateTypeRules.newRule(
                typeResolver.resolve(CollectionModel.class, PermissionResponse.class),
                PermissionResponseDoc.class
            ),
            AlternateTypeRules.newRule(
                typeResolver.resolve(CollectionModel.class, ProductResponse.class),
                ProductResponseDoc.class
            ),
            AlternateTypeRules.newRule(
                typeResolver.resolve(CollectionModel.class, RestaurantResponse.class),
                RestaurantResponseDoc.class
            ),
            AlternateTypeRules.newRule(
                typeResolver.resolve(CollectionModel.class, UserResponse.class),
                UserResponseDoc.class
            )
        ).toArray(new AlternateTypeRule[0]);
    }
    
    private Class<?>[] ignoredParameterTypes() {
        return Arrays.asList(
            ServletWebRequest.class,
            URL.class,
            URI.class,
            URLStreamHandler.class,
            Resource.class,
            File.class,
            InputStream.class,
            Pageable.class,
            Page.class,
            Sort.class
        ).toArray(new Class[0]);
    }
    
    private SecurityScheme securityScheme() {
        return new OAuthBuilder()
            .name("srs")
            .grantTypes(grantTypes())
            .scopes(scopes())
            .build();
    }
    
    private List<GrantType> grantTypes() {
        return Collections.singletonList(new ResourceOwnerPasswordCredentialsGrant("http://auth.springrestspecialist.local:8081/oauth/token"));
    }
    
    private List<AuthorizationScope> scopes() {
        return Arrays.asList(
            new AuthorizationScope("READ", "Read permission"),
            new AuthorizationScope("WRITE", "Create/Edit permission"),
            new AuthorizationScope("DELETE", "Delete permission")
        );
    }
    
    private SecurityContext securityContext() {
        SecurityReference securityReference = SecurityReference
            .builder()
            .reference("srs")
            .scopes(scopes().toArray(new AuthorizationScope[0]))
            .build();
        
        return SecurityContext.builder()
            .securityReferences(Collections.singletonList(securityReference))
            .forPaths(PathSelectors.any())
            .build();
    }
}
