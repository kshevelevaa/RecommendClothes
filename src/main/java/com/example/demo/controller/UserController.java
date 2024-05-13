package com.example.demo.controller;

import com.example.demo.dto.RegisterRq;
import com.example.demo.dto.UpdateUserProfileRq;
import com.example.demo.dto.UserProfileRs;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRq registerRq) {
        userService.addNewUser(registerRq);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile")
    public UserProfileRs getUserProfile(@AuthenticationPrincipal User user) {
        return userMapper.ofEntity(user);
    }

    @PutMapping("/update")
    public void updateUserProfile(@AuthenticationPrincipal User user,
                                  @RequestBody UpdateUserProfileRq updateUserProfileRq) {
        userMapper.ofRequest(user, updateUserProfileRq);
        userService.saveUser(user);
    }
}
