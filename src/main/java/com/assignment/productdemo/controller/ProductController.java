package com.assignment.productdemo.controller;

import com.assignment.productdemo.response.ErrorResponse;
import com.assignment.productdemo.dto.ProductDTO;
import com.assignment.productdemo.exception.ProductNotFoundException;
import com.assignment.productdemo.response.OkResponse;
import com.assignment.productdemo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<ProductDTO> getProductById(@RequestParam("id") long productId) {
        ProductDTO product = productService.getProductById(productId);
        if (product == null) {
            log.error("Fetching product with id = " + productId + " failed");
            throw new ProductNotFoundException("Product with id = " + productId + " was not found!");
        } else {
            log.info("Get product with id " + productId + " API invoked");
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<ProductDTO>> getAllProduct() {
        log.info("Get all product API invoked");
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProduct());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Validated @RequestBody ProductDTO product) {
        ProductDTO createdProduct = productService.createProduct(product);
        log.info("Create product API invoked for product: " + createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(@Validated @RequestBody ProductDTO product) {
        if (product.getId() == null) {
            log.error("Update product failed: product id is null");
            throw new ProductNotFoundException("Product id is null");
        }

        ProductDTO updatedProduct = productService.updateProduct(product);
        if (updatedProduct == null) {
            log.error("Update product failed: product could not be found");
            throw new ProductNotFoundException("Product not found!");
        } else {
            log.info("Product updated: " + updatedProduct);
            return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
        }
    }

    @DeleteMapping
    public ResponseEntity<OkResponse> deleteProduct(@RequestParam("id") long productId) {
        ProductDTO deletedProduct = productService.deleteProductById(productId);
        if (deletedProduct == null) {
            log.error("Product delete error: no product with id = " + productId + " exists");
            throw new ProductNotFoundException("Product with id " + productId + " is not found");
        } else {
            log.info("Product (id = " + productId + ") is deleted");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new OkResponse("Successfully deleted", deletedProduct));
        }
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> exceptionHandlerProductNotFound(ProductNotFoundException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> exceptionHandlerArgumentNotValid(MethodArgumentNotValidException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
