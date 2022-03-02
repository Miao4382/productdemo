package com.assignment.productdemo.service;

import com.assignment.productdemo.dao.ProductRepository;
import com.assignment.productdemo.dto.ProductDTO;
import com.assignment.productdemo.entity.Product;
import com.assignment.productdemo.exception.ProductNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO getProductById(long id) {
        Product product = productRepository.findById(id).orElse(null);
        return product == null ? null : modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(p -> modelMapper.map(p, ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product = productRepository.findById(productDTO.getId()).orElse(null);
        if (product != null) {
            product.setPrice(productDTO.getPrice());
            product.setName(productDTO.getName());
            product.setQuantity(productDTO.getQuantity());
            productRepository.save(product);
            return productDTO;
        }
        return null;
    }

    @Override
    public ProductDTO deleteProductById(long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            productRepository.deleteById(productId);
            return modelMapper.map(product, ProductDTO.class);
        }
        return null;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        productRepository.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }
}
