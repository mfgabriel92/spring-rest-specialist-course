package br.gabriel.springrestspecialist.api.controller.documentation;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import br.gabriel.springrestspecialist.api.model.request.ProductPhotoRequest;
import br.gabriel.springrestspecialist.api.model.response.ProductPhotoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Product photo")
public interface ProductPhotoDoc {
    @ApiOperation("Find a product photo of a product in JSON format")
    ProductPhotoResponse find(Integer id, Integer productId);

    @ApiOperation("Show the photo of a product")
    ResponseEntity<InputStreamResource> show(Integer id, Integer productId);

    @ApiOperation("Upload a photo of a product")
    ProductPhotoResponse save(Integer id, Integer productId, ProductPhotoRequest photoRequest) throws IOException;

    @ApiOperation("Remove a photo of a product")
    ResponseEntity<Void> delete(Integer id, Integer productId);
}
