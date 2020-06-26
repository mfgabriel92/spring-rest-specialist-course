package br.gabriel.springrestspecialist.domain.service;

import java.util.Optional;

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
        Integer productId = photo.getProduct().getId();
        Integer restaurantId = photo.getProduct().getRestaurant().getId();
        
        Optional<ProductPhoto> existingPhoto = productRepository.findPhotoById(productId, restaurantId);
        
        if (existingPhoto.isPresent()) {
            productRepository.delete(existingPhoto.get());
        }
        
        return productRepository.save(photo);
    }
}
