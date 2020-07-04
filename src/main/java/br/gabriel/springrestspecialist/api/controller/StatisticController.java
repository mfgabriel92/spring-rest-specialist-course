package br.gabriel.springrestspecialist.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.api.openapi.controller.StatisticDoc;
import br.gabriel.springrestspecialist.domain.filter.DailySalesFilter;
import br.gabriel.springrestspecialist.domain.model.aggregate.DailySales;
import br.gabriel.springrestspecialist.domain.service.OrderQueryService;

@RestController
@RequestMapping(path = "/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatisticController implements StatisticDoc {
    @Autowired
    private OrderQueryService orderQueryService;
    
    @Override
    @GetMapping("daily-sales")
    public List<DailySales> dailySales(DailySalesFilter filter) {
        return orderQueryService.getDailySales(filter);
    }
}
