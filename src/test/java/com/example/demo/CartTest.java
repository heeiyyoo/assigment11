package com.example.demo;

import com.example.demo.model.Cart;
import com.example.demo.model.User;
import com.example.demo.model.Wishlist;
import com.example.demo.service.CartService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CartTest {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;

    @Test
    public void createCart(){
        User user = new User();
        userService.add(user);

        Cart cart = new Cart();
        cartService.add(cart, user.getId());

        List<Cart> CartList = new ArrayList<>();
        CartList.add(userService.getById(user.getId()).getCart());
        Assertions.assertNotNull(CartList.get(0));

        cartService.deleteCart(user.getId(),cart.getId());

        Assertions.assertNull(userService.getById(user.getId()).getCart());

    }
}
