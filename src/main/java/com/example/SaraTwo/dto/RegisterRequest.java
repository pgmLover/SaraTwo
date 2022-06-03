package com.example.SaraTwo.dto;

import com.example.SaraTwo.customAnnotations.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String name;
    private String email;

    @Password
    private String password;
    private String phone;
}
