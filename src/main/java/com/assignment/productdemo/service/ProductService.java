package com.assignment.productdemo.service;

import com.assignment.productdemo.dto.ProductDTO;
import com.assignment.productdemo.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ProductDTO getProductById(long id);
    ProductDTO createProduct(ProductDTO productDTO);
    List<ProductDTO> getAllProduct();
    ProductDTO updateProduct(ProductDTO product);

    ProductDTO deleteProductById(long productId);
}
