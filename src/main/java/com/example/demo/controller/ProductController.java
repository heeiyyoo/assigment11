package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable Integer productId){
        return productService.getProductById(productId);
    }

    @GetMapping("/product_all")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/product_tocart/{cartId}")
    public Product addToCart(@PathVariable Integer cartId, @RequestBody Product product){
        return productService.addToCart(product,cartId);
    }

    @PostMapping("/product_towishlist/{wishlistId}")
    public Product addToWishlist(@PathVariable Integer wishlistId, @RequestBody Product product){
        return productService.addToWishlist(product,wishlistId);

    }

    @DeleteMapping("/product_popwishlist/{wishlistId}/{productId}")
    public void deleteFromWishlist(@PathVariable Integer wishlistId, @PathVariable Integer productId){
        productService.deleteFromWishlist(productId,wishlistId);
    }

    @DeleteMapping("/product_popcart/{cartId}/{productId}")
    public void deleteFromCart(@PathVariable Integer cartId, @PathVariable Integer productId){
        productService.deleteFromCart(productId,cartId);
    }

}
