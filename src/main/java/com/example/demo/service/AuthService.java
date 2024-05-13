package com.example.demo.service;

import com.example.demo.config.jwt.JwtTokenUtil;
import com.example.demo.dto.AuthRs;
import com.example.demo.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthRs authenticate(String login, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);
        try {
            authManager.authenticate(token);
        } catch (BadCredentialsException ex) {
            throw new AuthenticationCredentialsNotFoundException(String.format("User with login = %s can't auth", login));
        }
        User user = userService.getUserByLogin(login);
        return AuthRs.builder()
                .role(user.getRole().toString())
                .authToken(jwtTokenUtil.generateToken(user))
                .build();
    }
}
