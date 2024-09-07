package com.itg.webornek.feignClient;

import com.itg.webornek.model.Product;
import com.itg.webornek.model.ShoppingCart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@FeignClient(name = "shopping-cart-service")
@Component
public interface ShoppingCartClient {

    @PostMapping("shopping-cart-fc/create/empty")
    ResponseEntity<ShoppingCart> createCart();

    @PostMapping("shopping-cart-fc/{cartId}/addExistingProducts")
    ResponseEntity<ShoppingCart> addExistingProductsToCart(@RequestBody List<Product> products, @RequestParam("cartId") Long cartId);

    @GetMapping("shopping-cart-fc/findById/{id}")
    ResponseEntity<ShoppingCart> getCartById(@PathVariable("id") Long id);

    @PostMapping("shopping-cart-fc/{shoppingCartId}/removeProduct/{productId}")
    ResponseEntity<Void> removeProduct(@PathVariable("shoppingCartId") Long shoppingCartId,
                                       @PathVariable("productId") Long productId);

    @PostMapping("shopping-cart-fc//{shoppingCartId}/removeAllProducts")
    ResponseEntity<Void> removeAllProducts(@PathVariable("shoppingCartId") Long shoppingCartId);

}
