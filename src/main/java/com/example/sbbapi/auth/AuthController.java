package com.example.sbbapi.auth;

import com.example.sbbapi.auth.dto.SignInRequestDto;
import com.example.sbbapi.auth.dto.SignInResponseDto;
import com.example.sbbapi.auth.dto.SignUpRequestDto;
import com.example.sbbapi.auth.dto.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto result = this.authService.signUp(signUpRequestDto);
        return ResponseEntity.created(URI.create("/auth")).body(result);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto) {
        SignInResponseDto result = this.authService.signIn(signInRequestDto);
        return ResponseEntity.ok(result);
    }
}
