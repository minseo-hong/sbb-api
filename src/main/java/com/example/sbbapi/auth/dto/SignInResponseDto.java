package com.example.sbbapi.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInResponseDto {
    private String accessToken;
}
