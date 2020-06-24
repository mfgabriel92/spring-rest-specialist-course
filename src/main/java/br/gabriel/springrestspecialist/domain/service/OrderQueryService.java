package br.gabriel.springrestspecialist.domain.service;

import java.util.List;

import br.gabriel.springrestspecialist.domain.filter.DailySalesFilter;
import br.gabriel.springrestspecialist.domain.model.aggregate.DailySales;

public interface OrderQueryService {
    List<DailySales> getDailySales(DailySalesFilter filter);
}
