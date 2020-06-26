package br.gabriel.springrestspecialist.domain.repository;

import br.gabriel.springrestspecialist.domain.model.ProductPhoto;

public interface ProductPhotoRepository {
    ProductPhoto save(ProductPhoto photo);
    
    void delete(ProductPhoto photo);
}
