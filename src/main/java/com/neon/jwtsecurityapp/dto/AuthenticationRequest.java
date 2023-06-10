package com.neon.jwtsecurityapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthenticationRequest
{
    @NotNull(message = "email can't be null/ignored")
    @Email(message = "email invalid")
    private String email;
    @NotNull(message = "password can't be null/ignored")
    private String password;
}
