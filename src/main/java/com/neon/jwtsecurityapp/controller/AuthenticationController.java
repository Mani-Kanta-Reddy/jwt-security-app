package com.neon.jwtsecurityapp.controller;

import com.neon.jwtsecurityapp.dto.AuthenticationRequest;
import com.neon.jwtsecurityapp.dto.AuthenticationResponse;
import com.neon.jwtsecurityapp.dto.RegistrationRequest;
import com.neon.jwtsecurityapp.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController
{
    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        String token = authenticationService.authenticateAndGetToken(authenticationRequest);
        return AuthenticationResponse.builder()
            .token(token)
            .build();
    }

    @PostMapping("/register")
    public AuthenticationResponse register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        String token = authenticationService.generateToken(registrationRequest);
        return AuthenticationResponse.builder()
            .token(token)
            .build();
    }
}
