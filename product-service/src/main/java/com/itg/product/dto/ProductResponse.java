package com.itg.product.dto;

import com.itg.product.model.Product;

import java.math.BigDecimal;

public record ProductResponse(Long id, String name, String description, BigDecimal price) {

    public ProductResponse(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
}
