package com.example.demo.entity;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Базовый класс аккаунта со всеми валидными значениями
 */
public abstract class AbstractUserDetails implements UserDetails {
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
