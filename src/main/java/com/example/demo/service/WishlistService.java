package com.example.demo.service;

import com.example.demo.model.Wishlist;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;

    public List<Wishlist> findAll(){
        return wishlistRepository.findAll();
    }

    public Wishlist getById(Integer wishlistId){
        return wishlistRepository.findById(wishlistId).get();
    }
    public Wishlist add(Wishlist wishlist,Integer userId){
        userRepository.getById(userId).setWishlist(wishlist);
        return wishlistRepository.save(wishlist);
    }

    public  void delete(Integer wishlistId, Integer userId){
        userRepository.getById(userId).setWishlist(null);
         wishlistRepository.deleteById(wishlistId);
    }

}
