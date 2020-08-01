package br.gabriel.springrestspecialist.api.v1.controller;

import br.gabriel.springrestspecialist.api.v1.openapi.controller.StatisticDoc;
import br.gabriel.springrestspecialist.core.security.Permission;
import br.gabriel.springrestspecialist.domain.filter.DailySalesFilter;
import br.gabriel.springrestspecialist.domain.model.aggregate.DailySales;
import br.gabriel.springrestspecialist.domain.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.TemplateVariable.VariableType.REQUEST_PARAM;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/v1/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatisticController implements StatisticDoc {
    @Autowired
    private OrderQueryService orderQueryService;
    
    @Override
    @GetMapping
    public StatisticResponse statistics() {
        StatisticResponse statisticResponse = new StatisticResponse();
    
        TemplateVariables templateVariables = new TemplateVariables(
            new TemplateVariable("restaurantId", REQUEST_PARAM),
            new TemplateVariable("initialDate", REQUEST_PARAM),
            new TemplateVariable("endingDate", REQUEST_PARAM)
        );
        
        String url = linkTo(methodOn(StatisticController.class).dailySales(null)).toUri().toString();
        
        statisticResponse.add(Link.of(UriTemplate.of(url, templateVariables), "daily-dales"));
        
        return statisticResponse;
    }
    
    @Override
    @Permission.Statistic.CanRead
    @GetMapping("daily-sales")
    public List<DailySales> dailySales(DailySalesFilter filter) {
        return orderQueryService.getDailySales(filter);
    }
    
    public static class StatisticResponse extends RepresentationModel<StatisticResponse> {}
}
