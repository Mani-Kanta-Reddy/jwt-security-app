package com.neon.jwtsecurityapp;

import com.neon.jwtsecurityapp.entity.Role;
import com.neon.jwtsecurityapp.entity.User;
import com.neon.jwtsecurityapp.repository.UserRepository;
import com.neon.jwtsecurityapp.service.JwtService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JwtSecurityAppApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(JwtSecurityAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
        PasswordEncoder passwordEncoder,
        UserRepository userRepository,
        JwtService jwtService) {
        //SEED Admin user for testing.
        return args -> {
            User adminUser = User.builder()
                .name("admin")
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("password"))
                .role(Role.ADMIN)
                .build();
            userRepository.save(adminUser);
            System.out.println(jwtService.generateToken(adminUser));
        };
    }


}
