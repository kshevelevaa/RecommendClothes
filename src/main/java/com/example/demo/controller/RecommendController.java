package com.example.demo.controller;

import com.example.demo.entity.Clothes;
import com.example.demo.entity.User;
import com.example.demo.service.RecommendService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/recommend")
@RequiredArgsConstructor
public class RecommendController {
    private final RecommendService recommendService;
    private final UserService userService;

    @ResponseBody
    @GetMapping("/{city}")
    public ResponseEntity<?> getClothesByUserId(@AuthenticationPrincipal User user,
                                                @PathVariable String city) throws IOException {

        List<List<Clothes>> clothes = recommendService.recommendOptionalClothes(city, user.getUserId());

        if (clothes != null )
            return ResponseEntity.ok(clothes);
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping("/public/{city}")
    public ResponseEntity<?> getClothesByAdminId( @PathVariable String city) throws IOException {

        User admin = userService.getUserByLogin("admin");

        List<List<Clothes>> clothes = recommendService.recommendOptionalClothes(city, admin.getUserId());

        if (clothes != null )
            return ResponseEntity.ok(clothes);
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
