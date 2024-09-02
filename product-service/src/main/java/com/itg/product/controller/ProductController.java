package com.itg.product.controller;

import com.itg.product.dto.ProductRequest;
import com.itg.product.dto.ProductResponse;
import com.itg.product.model.Product;
import com.itg.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody ProductRequest productRequest){
       return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/findBy")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse findProductById(@RequestParam("id") Long id){
        return productService.findProductById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
