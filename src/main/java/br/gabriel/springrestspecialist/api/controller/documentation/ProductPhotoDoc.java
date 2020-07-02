package br.gabriel.springrestspecialist.api.controller.documentation;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import br.gabriel.springrestspecialist.api.model.request.ProductPhotoRequest;
import br.gabriel.springrestspecialist.api.model.response.ProductPhotoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Product photo")
public interface ProductPhotoDoc {
    @ApiOperation("Find a product photo of a product in JSON format")
    ProductPhotoResponse find(
        @ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The product ID", example = "1", required = true) Integer productId
    );

    @ApiOperation("Show the photo of a product")
    ResponseEntity<InputStreamResource> show(
        @ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The product ID", example = "1", required = true) Integer productId
    );

    @ApiOperation("Upload a photo of a product")
    ProductPhotoResponse save(
        @ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The product ID", example = "1", required = true) Integer productId,
        @ApiParam(value = "The product photo body", required = true) ProductPhotoRequest photoRequest
    ) throws IOException;

    @ApiOperation("Remove a photo of a product")
    ResponseEntity<Void> delete(
        @ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The product ID", example = "1", required = true) Integer productId
    );
}
