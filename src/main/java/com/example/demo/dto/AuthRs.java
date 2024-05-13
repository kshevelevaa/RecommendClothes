package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Тело ответа после авторизации
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRs {
    private String authToken;
    private String role;
}