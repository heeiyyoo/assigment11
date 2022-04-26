package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/cart/{cartId}")
    public Cart getCart(@PathVariable Integer cartId){
        return cartService.getById(cartId);
    }

    @GetMapping("/cart_all")
    public List<Cart> getAllCarts(){
        return cartService.findAll();
    }

    @PostMapping("/cart/{userId}")
    public Cart addCart(@PathVariable Integer userId, @RequestBody Cart cart){
        return cartService.add(cart,userId);
    }

    @DeleteMapping("/cart_del/{userId}/{cartId}")
    public void deleteCart(@PathVariable Integer userId,@PathVariable Integer cartId){
        cartService.deleteCart(userId,cartId);
    }
}
