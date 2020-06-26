package br.gabriel.springrestspecialist.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.model.ProductPhoto;
import br.gabriel.springrestspecialist.domain.repository.ProductRepository;
import br.gabriel.springrestspecialist.domain.service.StorageService.NewFile;

@Service
public class ProductPhotoService {
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private StorageService storage;
    
    @Transactional
    public ProductPhoto save(ProductPhoto photo, InputStream inputStream) {
        Integer productId = photo.getProduct().getId();
        Integer restaurantId = photo.getProduct().getRestaurant().getId();
        Optional<ProductPhoto> existingPhoto = productRepository.findPhotoById(productId, restaurantId);
        
        if (existingPhoto.isPresent()) {
            productRepository.delete(existingPhoto.get());
        }
        
        String filename = storage.generateFilename(photo.getFilename());
        
        photo.setFilename(filename);
        photo = productRepository.save(photo);
        store(photo, inputStream);
        
        return photo;
    }
    
    private void store(ProductPhoto photo, InputStream inputStream) {
        NewFile newFile = NewFile.builder()
            .filename(storage.generateFilename(photo.getFilename()))
            .inputStream(inputStream)
            .build();
        
        storage.store(newFile);
    }
}
