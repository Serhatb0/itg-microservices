package com.itg.product.service;

import com.itg.product.dto.ProductRequest;
import com.itg.product.dto.ProductResponse;
import com.itg.product.model.Product;
import com.itg.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;


    public Product createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
         productRepository.save(product);

        log.info("Product Created Successfully");
        return product;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(ProductResponse::new).toList();
    }


    public ProductResponse findProductById(Long id) {
        return productRepository.findById(id).map(ProductResponse::new).orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
