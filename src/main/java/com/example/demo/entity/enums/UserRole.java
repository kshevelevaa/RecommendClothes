package com.example.demo.entity.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * Роли пользователей:
 * <ul>
 *     <li>Обычный пользователь</li>
 *     <li>Администратор</li>
 * </ul>
 */
public enum UserRole {
    USER,
    ADMIN;

    public List<SimpleGrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(String.format("ROLE_%s", this.name())));
    }
}