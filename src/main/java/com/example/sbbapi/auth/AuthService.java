package com.example.sbbapi.auth;

import com.example.sbbapi.auth.dto.SignInRequestDto;
import com.example.sbbapi.auth.dto.SignInResponseDto;
import com.example.sbbapi.auth.dto.SignUpRequestDto;
import com.example.sbbapi.auth.dto.SignUpResponseDto;
import com.example.sbbapi.security.UserSecurityService;
import com.example.sbbapi.security.JwtUtil;
import com.example.sbbapi.user.User;
import com.example.sbbapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserSecurityService userDetailsService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        User user = User.builder()
                .username(signUpRequestDto.getUsername())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .role("ROLE_USER")
                .build();
        User newUser = this.userRepository.save(user);
        return SignUpResponseDto.fromEntity(newUser);
    }

    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                signInRequestDto.getUsername(), signInRequestDto.getPassword());
        this.authenticationManager.authenticate(authenticationToken);

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(signInRequestDto.getUsername());
        String accessToken = this.jwtUtil.generateToken(userDetails);
        return new SignInResponseDto(accessToken);
    }
}
