package br.gabriel.springrestspecialist.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;
import br.gabriel.springrestspecialist.domain.model.Product;
import br.gabriel.springrestspecialist.domain.model.ProductPhoto;
import br.gabriel.springrestspecialist.domain.model.Restaurant;
import br.gabriel.springrestspecialist.domain.repository.ProductRepository;
import br.gabriel.springrestspecialist.domain.repository.RestaurantRepository;
import br.gabriel.springrestspecialist.domain.service.StorageService.NewFile;

@Service
public class ProductPhotoService {
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Autowired
    private StorageService storage;
    
    @Transactional
    public ProductPhoto save(ProductPhoto photo, InputStream inputStream) {
        Integer productId = photo.getProduct().getId();
        Integer restaurantId = photo.getProduct().getRestaurant().getId();
        Optional<ProductPhoto> existingPhoto = productRepository.findPhotoById(productId, restaurantId);
        
        if (existingPhoto.isPresent()) {
            storage.remove(existingPhoto.get().getFilename());
            productRepository.delete(existingPhoto.get());
        }
        
        String filename = storage.generateFilename(photo.getFilename());
        
        photo.setFilename(filename);
        photo = productRepository.save(photo);
        store(photo, inputStream);
        
        return photo;
    }
    
    @Transactional
    public void delete(Integer id, Integer restaurantId) {
        ProductPhoto photo = findOrFail(id, restaurantId);
        storage.remove(photo.getFilename());
        productRepository.delete(photo);
    }
    
    private void store(ProductPhoto photo, InputStream inputStream) {
        NewFile newFile = NewFile.builder()
            .filename(photo.getFilename())
            .inputStream(inputStream)
            .build();
        
        storage.store(newFile);
    }
    
    public ProductPhoto findOrFail(Integer id, Integer restaurantId) {
        Product product = productRepository.findOrFail(id);
        Restaurant restaurant = restaurantRepository.findOrFail(restaurantId);
        
        return productRepository.findPhotoById(product.getId(), restaurant.getId())
            .orElseThrow(() -> new ResourceNotFoundExeption(String.format(
                "The photo for the product ID %d of the restaurant ID %d was not found", 
                product.getId(),
                restaurant.getId()
            )));
    }
}
