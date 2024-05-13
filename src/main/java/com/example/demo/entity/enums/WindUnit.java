package com.example.demo.entity.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Единицы измерения скорости ветра
 */
@Getter
@RequiredArgsConstructor
public enum WindUnit implements NamedDictionary {
    MC("М/с"),
    KMH("Км/ч");

    private final String name;

    public static final String CODE = "WIND_UNIT";
}
