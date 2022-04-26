package com.example.demo;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProductTest {
    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Test
    public void addProduct(){
        User user = new User();
        userService.add(user);

        Cart cart = new Cart();
        cartService.add(cart,user.getId());

        Product product = new Product();
        productService.addToCart(product,cart.getId());

        List<Product> productList = new ArrayList<>();
        productList.add(productService.getProductById(product.getId()));

        Assertions.assertNotNull(productList.get(0));

        productService.deleteFromCart(product.getId(),cart.getId());

        //Assertions.assertNull(cartService.getById(cart.getId()).getProductList());
        //will try to fix later
    }
}
