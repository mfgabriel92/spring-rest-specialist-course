package br.gabriel.springrestspecialist.infrastructure.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.gabriel.springrestspecialist.domain.filter.DailySalesFilter;
import br.gabriel.springrestspecialist.domain.model.Order;
import br.gabriel.springrestspecialist.domain.model.aggregate.DailySales;
import br.gabriel.springrestspecialist.domain.service.OrderQueryService;

@Repository
public class OrderQueryServiceImpl implements OrderQueryService {
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<DailySales> getDailySales(DailySalesFilter filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DailySales> query = builder.createQuery(DailySales.class);
        Root<Order> root = query.from(Order.class);
        
        Expression<Date> functionDateCratedAt = builder.function("DATE", Date.class, root.get("createdAt"));
        
        CompoundSelection<DailySales> select = builder.construct(
            DailySales.class, 
            functionDateCratedAt,
            builder.count(root.get("id")),
            builder.sum(root.get("grandTotal"))
        );
        
        query.select(select);
        query.groupBy(functionDateCratedAt);
        
        return entityManager.createQuery(query).getResultList();
    }
}
