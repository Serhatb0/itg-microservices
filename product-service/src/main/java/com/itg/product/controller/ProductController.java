package com.itg.product.controller;

import com.itg.product.dto.ProductRequest;
import com.itg.product.dto.ProductResponse;
import com.itg.product.model.Product;
import com.itg.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductService productService;
    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody ProductRequest productRequest){
       return productService.createProduct(productRequest);
    }
    @CrossOrigin
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }
    @CrossOrigin
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse findProductById(@PathVariable Long id){
        return productService.findProductById(id);
    }
    @CrossOrigin
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
