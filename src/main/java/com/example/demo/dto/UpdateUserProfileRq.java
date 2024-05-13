package com.example.demo.dto;

import com.example.demo.entity.enums.PressureUnit;
import com.example.demo.entity.enums.Sex;
import com.example.demo.entity.enums.TempUnit;
import com.example.demo.entity.enums.VisibilityUnit;
import com.example.demo.entity.enums.WindUnit;
import lombok.Getter;
import lombok.Setter;

/**
 * Запрос на обновление профиля пользователя
 */
@Getter
@Setter
public class UpdateUserProfileRq {
    private String username;

    private String email;

    private String phone;

    private Sex sex;

    private WindUnit windUnit;

    private TempUnit tempUnit;

    private PressureUnit pressureUnit;

    private VisibilityUnit visibilityUnit;

    private String pictureUrl;
}
