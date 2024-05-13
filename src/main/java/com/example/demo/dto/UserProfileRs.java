package com.example.demo.dto;

import com.example.demo.entity.enums.PressureUnit;
import com.example.demo.entity.enums.Sex;
import com.example.demo.entity.enums.TempUnit;
import com.example.demo.entity.enums.UserRole;
import com.example.demo.entity.enums.VisibilityUnit;
import com.example.demo.entity.enums.WindUnit;
import lombok.Getter;
import lombok.Setter;

/**
 * Представление профиля пользователя для UI
 */
@Getter
@Setter
public class UserProfileRs {
    private int userId;

    private String username;

    private UserRole role;

    private String email;

    private String phone;

    private Sex sex;

    private WindUnit windUnit;

    private TempUnit tempUnit;

    private PressureUnit pressureUnit;

    private VisibilityUnit visibilityUnit;

    private String pictureUrl;
}
