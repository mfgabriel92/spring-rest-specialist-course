package br.gabriel.springrestspecialist.infrastructure.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import br.gabriel.springrestspecialist.domain.repository.BaseJpaRepository;

public class BaseJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseJpaRepository<T, ID> {
	private EntityManager entityManager;
	
	public BaseJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public Optional<T> findFirst() {
		String jpql = String.format("FROM %s", getDomainClass().getName());
		
		T entity = entityManager
			.createQuery(jpql, getDomainClass())
			.setMaxResults(1)
			.getSingleResult();
		
		return Optional.ofNullable(entity);
	}
}
	