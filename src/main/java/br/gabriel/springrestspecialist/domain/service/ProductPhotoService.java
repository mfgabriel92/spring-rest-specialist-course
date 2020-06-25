package br.gabriel.springrestspecialist.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.model.ProductPhoto;
import br.gabriel.springrestspecialist.domain.repository.ProductRepository;

@Service
public class ProductPhotoService {
    @Autowired
    private ProductRepository productRepository;
    
    @Transactional
    public ProductPhoto save(ProductPhoto photo) {
        return productRepository.save(photo);
    }
}
