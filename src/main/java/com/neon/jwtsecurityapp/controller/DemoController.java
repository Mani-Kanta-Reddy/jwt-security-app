package com.neon.jwtsecurityapp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController
{
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public String helloUser() {
        return "hello User";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String helloAdmin() {
        return "hello Admin";
    }
}
