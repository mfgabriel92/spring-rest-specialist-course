package br.gabriel.springrestspecialist.api.openapi.controller;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import br.gabriel.springrestspecialist.api.model.request.ProductPhotoRequest;
import br.gabriel.springrestspecialist.api.model.response.ProductPhotoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurant")
public interface ProductPhotoDoc {
    @ApiOperation("Find a product photo of a product in JSON format")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    ProductPhotoResponse find(
        @ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The product ID", example = "1", required = true) Integer productId
    );

    @ApiOperation("Show the photo of a product")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    ResponseEntity<InputStreamResource> show(
        @ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The product ID", example = "1", required = true) Integer productId
    );

    @ApiOperation("Upload a photo of a product")
    ProductPhotoResponse save(
        @ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The product ID", example = "1", required = true) Integer productId,
        ProductPhotoRequest photoRequest,
        @ApiParam(value = "The product photo (500KB; PNG or JPEG)", required = true) MultipartFile file
    ) throws IOException;

    @ApiOperation("Remove a photo of a product")
    ResponseEntity<Void> delete(
        @ApiParam(value = "The restaurant ID", example = "1", required = true) Integer id, 
        @ApiParam(value = "The product ID", example = "1", required = true) Integer productId
    );
}
