package br.gabriel.springrestspecialist.api.controller.documentation;

import java.util.List;

import br.gabriel.springrestspecialist.domain.filter.DailySalesFilter;
import br.gabriel.springrestspecialist.domain.model.aggregate.DailySales;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Statistic")
public interface StatisticDoc {
    @ApiOperation("List all the daily sales for a period")
    List<DailySales> dailySales(DailySalesFilter filter);
}
