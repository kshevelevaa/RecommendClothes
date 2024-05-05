package com.example.demo.dto;

import java.io.Serializable;

public record AuthRequestDto(String username, String password) implements Serializable {
}
