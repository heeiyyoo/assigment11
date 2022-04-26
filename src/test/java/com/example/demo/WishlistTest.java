package com.example.demo;

import com.example.demo.model.Cart;
import com.example.demo.model.User;
import com.example.demo.model.Wishlist;
import com.example.demo.service.CartService;
import com.example.demo.service.UserService;
import com.example.demo.service.WishlistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class WishlistTest {
    @Autowired
    WishlistService wishlistService;
    @Autowired
    UserService userService;

    @Test
    public void createWishlist(){
        User user = new User();
        userService.add(user);

        Wishlist wishlist = new Wishlist();
        wishlistService.add(wishlist, user.getId());

        List<Wishlist> wishlistList = new ArrayList<>();
        wishlistList.add(userService.getById(user.getId()).getWishlist());

        Assertions.assertNotNull(wishlistList.get(0));

        wishlistService.delete(wishlist.getId(),user.getId());

        Assertions.assertNull(userService.getById(user.getId()).getWishlist());
    }
}
