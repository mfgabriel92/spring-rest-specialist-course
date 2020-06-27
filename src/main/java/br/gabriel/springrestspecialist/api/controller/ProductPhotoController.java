package br.gabriel.springrestspecialist.api.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.api.model.mapper.ProductPhotoMapper;
import br.gabriel.springrestspecialist.api.model.request.ProductPhotoRequest;
import br.gabriel.springrestspecialist.api.model.response.ProductPhotoResponse;
import br.gabriel.springrestspecialist.domain.exception.ResourceNotFoundExeption;
import br.gabriel.springrestspecialist.domain.model.Product;
import br.gabriel.springrestspecialist.domain.model.ProductPhoto;
import br.gabriel.springrestspecialist.domain.repository.ProductRepository;
import br.gabriel.springrestspecialist.domain.service.ProductPhotoService;
import br.gabriel.springrestspecialist.domain.service.StorageService;

@RestController
@RequestMapping("/restaurants/{id}/products/{productId}/photo")
public class ProductPhotoController {
    @Autowired
    private ProductPhotoService service;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductPhotoMapper mapper;
    
    @Autowired
    private StorageService storage;
    
    @GetMapping
    public ProductPhotoResponse find(@PathVariable Integer id, @PathVariable Integer productId) {
        return mapper.toModel(service.findOrFail(productId, id));
    }
    
    @GetMapping(produces = { MediaType.IMAGE_JPEG_VALUE , MediaType.IMAGE_PNG_VALUE })
    public ResponseEntity<InputStreamResource> show(@PathVariable Integer id, @PathVariable Integer productId) {
        try {
            ProductPhoto photo = service.findOrFail(productId, id);
            InputStream inputStream = storage.find(photo.getFilename());
            
            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(photo.getContentType()))
                .body(new InputStreamResource(inputStream));
        } catch (ResourceNotFoundExeption e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductPhotoResponse save(@PathVariable Integer id, @PathVariable Integer productId, @Valid ProductPhotoRequest photoRequest) throws IOException {
        Product product = productRepository.findOrFail(productId);

        ProductPhoto photo = new ProductPhoto();
        photo.setFilename(photoRequest.getFile().getOriginalFilename());
        photo.setContentType(photoRequest.getFile().getContentType());
        photo.setDescription(photoRequest.getDescription());
        photo.setSize((int) photoRequest.getFile().getSize());
        photo.setProduct(product);
        
        return mapper.toModel(service.save(photo, photoRequest.getFile().getInputStream()));
    }
    
    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Integer id, @PathVariable Integer productId) {
        try {
            service.delete(productId, id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundExeption e) {
            return ResponseEntity.notFound().build();
        }
    }
}
