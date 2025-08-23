package com.example.sbbapi.auth.dto;

import lombok.Getter;

@Getter
public class SignInRequestDto {
    private String username;
    private String password;
}
