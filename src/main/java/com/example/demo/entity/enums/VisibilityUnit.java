package com.example.demo.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Единицы измерения видимости
 */
@Getter
@RequiredArgsConstructor
public enum VisibilityUnit implements NamedDictionary {
    METERS("Метры"),
    KILOMETERS("Километры");

    private final String name;

    public static final String CODE = "VISIBILITY_UNIT";
}
