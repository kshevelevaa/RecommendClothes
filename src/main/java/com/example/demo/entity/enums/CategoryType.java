package com.example.demo.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryType implements NamedDictionary {
    TOP("Топ"),
    BOTTOM("Низ"),
    FULL("Полный"),
    SHOES("Обувь"),
    OUTERWEAR("Верхняя одежда"),
    HEADDRESS("Головной убор");

    private final String name;

    public static final String CODE = "CATEGORY_TYPE";
}
