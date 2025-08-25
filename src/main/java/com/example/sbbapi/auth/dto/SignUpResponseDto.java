package com.example.sbbapi.auth.dto;

import com.example.sbbapi.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpResponseDto {
    private String username;

    public static SignUpResponseDto fromEntity(User user) {
        return SignUpResponseDto.builder()
                .username(user.getUsername())
                .build();
    }
}
