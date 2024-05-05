package com.example.demo.dto;

import java.io.Serializable;

public record AuthRequestDto(String email, String password) implements Serializable {
}
