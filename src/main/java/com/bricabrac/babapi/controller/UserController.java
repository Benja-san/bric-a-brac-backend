package com.bricabrac.babapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bricabrac.babapi.entity.User;
import com.bricabrac.babapi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(
        UserService userService
        ) {
        this.userService = userService;
    }

    @Operation(summary = "Find users", description = "Find all users")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/users/")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @Operation(summary = "Find user", description = "Find a user")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/auth/register")
    public User register(String username, String password, String email) {
        return userService.register(username, password, email);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody User user) {
        System.out.println(user.getEmail());
        return userService.login(user.getEmail(), user.getPassword());
    }
}
