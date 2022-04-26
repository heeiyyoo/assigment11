package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private  final UserService userService;

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable Integer userId){
        return userService.getById(userId);
    }

    @GetMapping("/users_all")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @PostMapping("/user_add")
    public User addUser(@RequestBody User user){
        return userService.add(user);
    }

    @DeleteMapping("user_remove/{userId}")
    public void deleteUser(@PathVariable Integer userId){
        userService.deleteById(userId);
    }

}
