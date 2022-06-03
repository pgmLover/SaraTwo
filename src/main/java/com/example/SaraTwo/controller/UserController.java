package com.example.SaraTwo.controller;

import com.example.SaraTwo.dto.RegisterRequest;
import com.example.SaraTwo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterRequest registerRequest) {

        return userService.register(registerRequest);
    }
}
