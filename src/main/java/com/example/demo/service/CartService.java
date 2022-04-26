package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public List<Cart> findAll(){
        return cartRepository.findAll();
    }

    public Cart getById(Integer id){
        return cartRepository.findById(id).get();
    }
    public Cart add(Cart cart,Integer userId){
        userRepository.getById(userId).setCart(cart);
        return cartRepository.save(cart);
    }

    public void deleteCart(Integer userId,Integer cartId){
        userRepository.getById(userId).setCart(null);
        cartRepository.deleteById(cartId);
    }



}
