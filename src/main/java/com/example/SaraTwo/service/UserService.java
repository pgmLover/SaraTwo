package com.example.SaraTwo.service;

import com.example.SaraTwo.dto.RegisterRequest;
import com.example.SaraTwo.entity.User;
import com.example.SaraTwo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public  String register(RegisterRequest registerRequest){
        userRepository.save(User.builder().name(registerRequest.getName()).email(registerRequest.getEmail())
                .phone(registerRequest.getPhone()).password(registerRequest.getPassword()).build());
        return "User created successfully";
    }

}
