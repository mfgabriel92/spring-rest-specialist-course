package br.gabriel.springrestspecialist.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import br.gabriel.springrestspecialist.domain.model.Product;
import br.gabriel.springrestspecialist.domain.model.ProductPhoto;

public interface ProductRepository extends BaseJpaRepository<Product, Integer>, ProductPhotoRepository {
    @Query("FROM Product WHERE id = :id AND restaurant.id = :restaurantId")
    Optional<Product> findById(Integer id, Integer restaurantId);
    
    @Query("SELECT pp FROM ProductPhoto pp JOIN pp.product p WHERE pp.product.id = :id AND p.restaurant.id = :restaurantId")
    Optional<ProductPhoto> findPhotoById(Integer id, Integer restaurantId);
}