package com.example.demo.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Единицы измерения давления
 */
@Getter
@RequiredArgsConstructor
public enum PressureUnit implements NamedDictionary {
    MBAR("Бар"),
    MERCURY_MM("Мм. рт. ст.");

    private final String name;

    public static final String CODE = "PRESSURE_UNIT";
}
