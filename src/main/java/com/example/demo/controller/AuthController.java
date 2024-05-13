package com.example.demo.controller;

import com.example.demo.dto.AuthRq;
import com.example.demo.dto.AuthRs;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/login")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public AuthRs auth(@RequestBody AuthRq authRq) {
        return authService.authenticate(authRq.login(), authRq.password());
    }
}
