package com.neon.jwtsecurityapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RegistrationRequest
{
    @NotNull(message = "name can't be null/ignored")
    private String name;
    @NotNull(message = "email can't be null/ignored")
    @Email(message = "email invalid")
    private String email;
    @NotNull(message = "password can't be null/ignored")
    private String password;
}
