package com.example.demo.service;

import com.example.demo.dto.RegisterRq;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.UserRole;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User addNewUser(RegisterRq registerRequest) {
        User userAccount =
                User.builder()
                        .username(registerRequest.login())
                        .role(UserRole.USER)
                        .password(passwordEncoder.encode(registerRequest.password()))
                        .build();
        User savedUser;
        try {
            savedUser = userRepository.save(userAccount);
        } catch (DataIntegrityViolationException ex) {
            log.warn("Попытка регистрации с существующим логином = {}", registerRequest.login());
            throw new RuntimeException(
                    String.format("Логин '%s' уже используется", registerRequest.login()));
        }
        log.info("Пользователь с логином = {} успешно зарегистрировался", registerRequest.login());
        return savedUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User getUserByLogin(String username) {
        return userRepository.findByUsername(username);
    }
}
