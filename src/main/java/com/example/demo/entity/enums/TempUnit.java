package com.example.demo.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Единицы измерения температуры
 */
@Getter
@RequiredArgsConstructor
public enum TempUnit implements NamedDictionary {
    C("По Цельсию"),
    F("По Фаренгейту");

    private final String name;

    public static final String CODE = "TEMPERATURE_UNIT";
}
