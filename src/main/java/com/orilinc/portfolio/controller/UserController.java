package com.orilinc.portfolio.controller;

import com.orilinc.portfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.orilinc.portfolio.model.User;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    public User addNewUser (@RequestParam String name, @RequestParam String email) {
        User user = User.builder()
                        .name(name)
                        .email(email)
                        .build();
        return userService.addUser(user);
    }

    @GetMapping("/all")
    public Iterable<User> getAllUsers() {
        return userService.findAllUsers();
    }
}
