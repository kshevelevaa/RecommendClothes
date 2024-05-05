package com.example.demo.controller;

import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.JwtResponseDto;
import com.example.demo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/login", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService) {
        this.authService = authService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/auth")
    public JwtResponseDto auth(@RequestBody AuthRequestDto authRequestDto) {
        return authService.getJwtDtoByAuthRequest(authRequestDto);
    }

}
