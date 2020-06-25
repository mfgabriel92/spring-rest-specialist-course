package br.gabriel.springrestspecialist.api.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import br.gabriel.springrestspecialist.core.validation.FileSize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoRequest {
    @NotNull
    @FileSize(maxSize = "500KB")
    private MultipartFile file;
    
    @NotBlank
    private String description;
}
