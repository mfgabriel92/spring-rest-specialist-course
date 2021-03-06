package br.gabriel.springrestspecialist.api.v1.openapi.controller;

import br.gabriel.springrestspecialist.api.v1.controller.StatisticController.StatisticResponse;
import br.gabriel.springrestspecialist.domain.filter.DailySalesFilter;
import br.gabriel.springrestspecialist.domain.model.aggregate.DailySales;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(tags = "Statistic")
public interface StatisticDoc {
    StatisticResponse statistics();
    
    @ApiImplicitParams({
        @ApiImplicitParam(name = "restaurantId", value = "The restaurant ID", dataType = "int", example = "1"),
        @ApiImplicitParam(name = "initialDate", value = "The initial date", dataType = "date-time", example = "2020-07-07T00:00:00Z"),
        @ApiImplicitParam(name = "endingDate", value = "The ending date", dataType = "date-time", example = "2020-07-08T00:00:00Z"),
    })
    @ApiOperation("List all the daily sales for a period")
    List<DailySales> dailySales(DailySalesFilter filter);
}
