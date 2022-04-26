package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User getById(Integer id){
        return userRepository.findById(id).get();
    }

    public User add(User user){
        return userRepository.save(user);
    }
    public void deleteById(Integer id){
        userRepository.deleteById(id);
    }
    @Override
    public UserDetails loadUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

}
