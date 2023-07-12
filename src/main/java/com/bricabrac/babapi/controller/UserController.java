package com.bricabrac.babapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bricabrac.babapi.entity.User;
import com.bricabrac.babapi.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(
        UserService userService
        ) {
        this.userService = userService;
    }

    @GetMapping("/api/users/")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("api/register/")
    public User register(String username, String password, String email) {
        return userService.register(username, password, email);
    }
}
