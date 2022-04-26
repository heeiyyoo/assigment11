package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public  Product getProductById(Integer productId){
        return productRepository.findById(productId).get();
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product addToCart(Product product, Integer cartId){
        cartRepository.getById(cartId).getProductList().add(product);
        return productRepository.save(product);
    }

    public Product addToWishlist(Product product,Integer userId){
        userRepository.getById(userId).getWishlist().getProductList().add(product);
        return productRepository.save(product);
    }

    public void deleteFromCart(Integer productId,Integer cartId){
        cartRepository.getById(cartId).getProductList().remove(productRepository.getById(productId));
        productRepository.deleteById(productId);
    }

    public void deleteFromWishlist(Integer productId,Integer userId){
        userRepository.getById(userId).getWishlist().getProductList().remove((productRepository.getById(productId)));
        productRepository.deleteById((productId));
    }
}
