package br.gabriel.springrestspecialist.api.controller;

import java.nio.file.Path;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gabriel.springrestspecialist.api.model.request.ProductPhotoRequest;

@RestController
@RequestMapping("/restaurants/{id}/products/{productId}/photo")
public class RestaurantPhotoController {
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void save(@PathVariable Integer id, @PathVariable Integer productId, ProductPhotoRequest photoRequest) {
        String filename = UUID.randomUUID().toString() + "_" + photoRequest.getFile().getOriginalFilename();
        Path dest = Path.of("/home/gabriel/Downloads", filename);
        
        System.out.println(filename);
        System.out.println(photoRequest.getDescription());
        
        try {
            photoRequest.getFile().transferTo(dest);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
