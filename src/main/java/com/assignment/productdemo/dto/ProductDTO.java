package com.assignment.productdemo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductDTO {
    private Long id;
    @NotNull(message = "Product name must not be null")
    private String name;
    private Double price;
    private Integer quantity;
}
