package com.example.sbbapi.security;

import java.util.Arrays;
import java.util.stream.Stream;

public class PermittedPath {
    private final static String[] COMMON_PATHS = {
            "/auth/sign-up",
            "/auth/sign-in",
            "/actuator/health",
    };

    private final static String[] SWAGGER_PATHS = {
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/webjars/**",
            "/swagger-resources/**"
    };

    public static final String[] ALL_PATHS = Stream.concat(
            Arrays.stream(COMMON_PATHS),
            Arrays.stream(SWAGGER_PATHS)
    ).toArray(String[]::new);
}
