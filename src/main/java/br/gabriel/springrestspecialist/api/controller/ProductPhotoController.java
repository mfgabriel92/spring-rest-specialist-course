package br.gabriel.springrestspecialist.api.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.api.model.mapper.ProductPhotoMapper;
import br.gabriel.springrestspecialist.api.model.request.ProductPhotoRequest;
import br.gabriel.springrestspecialist.api.model.response.ProductPhotoResponse;
import br.gabriel.springrestspecialist.domain.model.Product;
import br.gabriel.springrestspecialist.domain.model.ProductPhoto;
import br.gabriel.springrestspecialist.domain.repository.ProductRepository;
import br.gabriel.springrestspecialist.domain.service.ProductPhotoService;

@RestController
@RequestMapping("/restaurants/{id}/products/{productId}/photo")
public class ProductPhotoController {
    @Autowired
    private ProductPhotoService service;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductPhotoMapper mapper;
    
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
}
