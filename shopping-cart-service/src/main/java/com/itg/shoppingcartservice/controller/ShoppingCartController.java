package com.itg.shoppingcartservice.controller;

import com.itg.shoppingcartservice.model.Product;
import com.itg.shoppingcartservice.model.ShoppingCart;
import com.itg.shoppingcartservice.service.ShoppingCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("shopping-cart-fc")
@CrossOrigin
public class ShoppingCartController {


    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping("/create")
    public ResponseEntity<ShoppingCart> create(@RequestParam("name") String name) {
        return shoppingCartService.create(name);
    }

    @PostMapping("{id}")
    public ResponseEntity<ShoppingCart> addProducts(
            @PathVariable("id") Long shoppingCartId, @RequestBody List<Product> products) {
        return shoppingCartService.addProducts(shoppingCartId, products);
    }


    @GetMapping("{id}")
    public ResponseEntity<Map<String, String>> getShoppingCartPrice(
            @PathVariable("id") Long shoppingCartId) {
        return shoppingCartService.getShoppingCartPrice(shoppingCartId);
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCartFindById(
            @PathVariable("id") Long shoppingCartId) {
        return ResponseEntity.ok(shoppingCartService.getShoppingCartFindById(shoppingCartId));
    }

}