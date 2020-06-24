package br.gabriel.springrestspecialist.infrastructure.service;

import java.util.List;

import br.gabriel.springrestspecialist.domain.filter.DailySalesFilter;
import br.gabriel.springrestspecialist.domain.model.aggregate.DailySales;
import br.gabriel.springrestspecialist.domain.service.OrderQueryService;

public class OrderQueryServiceImpl implements OrderQueryService {
    @Override
    public List<DailySales> getDailySales(DailySalesFilter filter) {
        return null;
    }
}
