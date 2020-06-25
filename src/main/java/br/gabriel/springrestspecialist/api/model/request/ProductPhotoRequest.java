package br.gabriel.springrestspecialist.api.model.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoRequest {
    private MultipartFile file;
    
    private String description;
}
