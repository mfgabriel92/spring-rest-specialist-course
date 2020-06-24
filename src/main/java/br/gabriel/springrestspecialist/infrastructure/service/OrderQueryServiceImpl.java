package br.gabriel.springrestspecialist.infrastructure.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.gabriel.springrestspecialist.domain.filter.DailySalesFilter;
import br.gabriel.springrestspecialist.domain.model.Order;
import br.gabriel.springrestspecialist.domain.model.OrderStatus;
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
        List<Predicate> predicates = new ArrayList<>();
        
        Expression<Date> functionDateCratedAt = builder.function("DATE", Date.class, root.get("createdAt"));
        
        CompoundSelection<DailySales> select = builder.construct(
            DailySales.class, 
            functionDateCratedAt,
            builder.count(root.get("id")),
            builder.sum(root.get("grandTotal"))
        );
        
        if (filter.getRestaurantId() != null) {
            predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
        } else if (filter.getInitialDate() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("createdAt"), filter.getInitialDate()));
        } else if (filter.getEndingDate() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("createdAt"), filter.getEndingDate()));
        }
        
        predicates.add(root.get("status").in(Arrays.asList(OrderStatus.CONFIRMED, OrderStatus.DELIVERED)));
        
        query.select(select);
        query.where(builder.and(predicates.toArray(new Predicate[0])));
        query.groupBy(functionDateCratedAt);
        
        return entityManager.createQuery(query).getResultList();
    }
}
