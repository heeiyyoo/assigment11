package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.Wishlist;
import com.example.demo.repository.WishlistRepository;
import com.example.demo.service.CartService;
import com.example.demo.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;

    @GetMapping("/wishlist/{wishlistId}")
    public Wishlist getWishlist(@PathVariable Integer wishlistId){
        return wishlistService.getById(wishlistId);
    }

    @GetMapping("/wishlist_all")
    public List<Wishlist> getAllWishlists(){
        return wishlistService.findAll();
    }

    @PostMapping("/wishlist/{wishlistId}")
    public Wishlist addWishlist(@PathVariable Integer userId, @RequestBody Wishlist wishlist){
        return wishlistService.add(wishlist,userId);
    }

    @DeleteMapping("/wishlist_del/{userId}/{wishlistId}")
    public void deleteCart(@PathVariable Integer userId,@PathVariable Integer wishlistId){
        wishlistService.delete(userId,wishlistId);
    }
}
