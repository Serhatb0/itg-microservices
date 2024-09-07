package com.itg.webornek.feignClient;

import com.itg.webornek.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "product-service")
@Component
public interface ProductClient {

    @GetMapping("/product")
    List<Product> getAllProducts();

    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable("id") Long id);

    @PostMapping("/product")
    Product createProduct(Product product);

    @DeleteMapping("/product/{id}")
    String deleteProduct(@PathVariable("id") Long id);
}