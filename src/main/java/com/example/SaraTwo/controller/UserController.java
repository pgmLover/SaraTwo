package com.example.SaraTwo.controller;

import com.example.SaraTwo.dto.LoginRequest;
import com.example.SaraTwo.dto.RegisterRequest;
import com.example.SaraTwo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ModelAndView register(@RequestBody @Valid RegisterRequest registerRequest) {

        String str = userService.register(registerRequest);
        return new ModelAndView("redirect:/fileupload");
    }

    @GetMapping("/welcome")
    public String home() {
        return "Welcome to mini project";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.passwordVerifying(loginRequest));
    }


    @PostMapping("/fileupload")
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) {

        try {
            if (file.isEmpty()) {
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File is Empty");
            }
            if (!file.getContentType().equals("image/png")) {
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only png content type are allowed");
            }
            boolean uploadStatus = userService.fileUpload(file);
            if (uploadStatus) {
                return ResponseEntity.ok("File uploaded successfully");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong please try again.");


    }
}
