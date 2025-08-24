package com.example.sbbapi.security;

import java.util.Arrays;
import java.util.stream.Stream;

public class PermittedPath {
    private final static String[] SWAGGER_PATHS = {
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/webjars/**",
            "/swagger-resources/**"
    };
    private final static String[] COMMON_PATHS = {
            "/auth/sign-up",
            "/auth/sign-in",
            "/actuator/health",
    };

    public final static String[] ALL_PATHS = Stream.concat(
            Arrays.stream(SWAGGER_PATHS),
            Arrays.stream(COMMON_PATHS)
    ).toArray(String[]::new);
}
