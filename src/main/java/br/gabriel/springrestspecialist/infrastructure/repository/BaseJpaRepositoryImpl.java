package br.gabriel.springrestspecialist.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;
import br.gabriel.springrestspecialist.domain.repository.BaseJpaRepository;

public class BaseJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseJpaRepository<T, ID> {
	private EntityManager entityManager;
	
	public BaseJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public T findOrFail(ID id) {
	    String jpql = String.format("FROM %s WHERE id = :id", getDomainClass().getName());

	    try {
	        return entityManager
	            .createQuery(jpql, getDomainClass())
	            .setParameter("id", id)
	            .getSingleResult();
	    } catch (NoResultException e) {
	        throw new ResourceNotFoundExeption(String.format(
	        	"The %s ID %d was not found",
	        	getDomainClass().getSimpleName().toLowerCase(),
	        	id
	        ));
	    }
	}
	
	@Transactional
	@Override
    public void deleteOrFail(ID id) {
        entityManager.remove(findOrFail(id));
    }
}
