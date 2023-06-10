package com.neon.jwtsecurityapp.service;

import com.neon.jwtsecurityapp.dto.AuthenticationRequest;
import com.neon.jwtsecurityapp.dto.RegistrationRequest;
import com.neon.jwtsecurityapp.entity.Role;
import com.neon.jwtsecurityapp.entity.User;
import com.neon.jwtsecurityapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService
{
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    public String authenticateAndGetToken(AuthenticationRequest authenticationRequest) throws AuthenticationException
    {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
        );

        User user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();

        return jwtService.generateToken(user);
    }

    public String generateToken(RegistrationRequest registrationRequest)
    {
        //create user from the auth-request and store to the DB and return the token
        User user = User.builder()
            .name(registrationRequest.getName())
            .email(registrationRequest.getEmail())
            .password(passwordEncoder.encode(registrationRequest.getPassword()))
            .role(Role.USER)
            .build();

        userRepository.save(user);

        return jwtService.generateToken(user);
    }
}
