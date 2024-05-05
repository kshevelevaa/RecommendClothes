package com.example.demo.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

public record JwtResponseDto(String token, OffsetDateTime issuedAt, OffsetDateTime expiration)
        implements Serializable {}
