package br.gabriel.springrestspecialist.api.v1.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import br.gabriel.springrestspecialist.core.validation.ContentType;
import br.gabriel.springrestspecialist.core.validation.FileSize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoRequest {
    @ApiModelProperty(hidden = true)
    @NotNull
    @FileSize("500KB")
    @ContentType({ MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE })
    private MultipartFile file;
    
    @ApiModelProperty(required = true)
    @NotBlank
    private String description;
}
