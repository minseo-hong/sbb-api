package com.example.sbbapi.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SignUpRequestDto {
    private String username;
    private String password;
}
