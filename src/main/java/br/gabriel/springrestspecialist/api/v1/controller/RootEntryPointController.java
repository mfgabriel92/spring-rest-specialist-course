package br.gabriel.springrestspecialist.api.v1.controller;

import io.swagger.annotations.Api;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Api(tags = "Root")
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {
    @GetMapping
    public RootEntryPointModel root() {
        RootEntryPointModel rootEntryPointModel = new RootEntryPointModel();
    
        rootEntryPointModel.add(linkTo(CityController.class).withRel("cities"));
        rootEntryPointModel.add(linkTo(CuisineController.class).withRel("cuisines"));
        rootEntryPointModel.add(linkTo(GroupController.class).withRel("groups"));
        rootEntryPointModel.add(linkTo(OrderController.class).withRel("orders"));
        rootEntryPointModel.add(linkTo(PaymentMethodController.class).withRel("payment-methods"));
        rootEntryPointModel.add(linkTo(PermissionController.class).withRel("permissions"));
        rootEntryPointModel.add(linkTo(RestaurantController.class).withRel("restaurants"));
        rootEntryPointModel.add(linkTo(StateController.class).withRel("states"));
        rootEntryPointModel.add(linkTo(StatisticController.class).withRel("statistics"));
        rootEntryPointModel.add(linkTo(UserController.class).withRel("users"));
    
        return rootEntryPointModel;
    }
    
    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {}
}
