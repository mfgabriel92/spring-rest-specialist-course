package br.gabriel.springrestspecialist.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.model.ProductPhoto;
import br.gabriel.springrestspecialist.domain.repository.ProductPhotoRepository;

@Repository
public class ProductRepositoryImpl implements ProductPhotoRepository {
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    @Override
    public ProductPhoto save(ProductPhoto photo) {
        return entityManager.merge(photo);
    }

}
