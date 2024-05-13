package com.example.demo.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Sex implements NamedDictionary {
    FEMALE("Женский"),
    MALE("Мужской"),
    UNISEX("Унисекс");

    public static final String CODE = "SEX";

    private final String name;
}
