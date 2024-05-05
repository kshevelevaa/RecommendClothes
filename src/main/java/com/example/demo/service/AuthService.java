package com.example.demo.service;


import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.JwtResponseDto;

/**
 * @author Aleksandr Slotin 2022 Dec
 */
public interface AuthService {

    void authorizeUserByToken(String token);

    JwtResponseDto getJwtDtoByAuthRequest(AuthRequestDto authRequestDto);
}
