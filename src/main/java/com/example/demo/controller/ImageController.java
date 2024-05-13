package com.example.demo.controller;

import com.example.demo.entity.Clothes;
import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import com.example.demo.service.ClothesService;
import com.example.demo.service.ImageService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    private final ClothesService clothesService;
    private final UserService userService;

    @PostMapping("/image/cloth/{clothId}")
    ResponseEntity<?> uploadClothImage(@PathVariable int clothId,
                               @RequestParam("file") MultipartFile image) {
        Clothes clothes = clothesService.findById(clothId);
        Image uploaded = imageService.uploadImage(image);

        clothes.setImage(uploaded);
        clothesService.save(clothes);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/image/user")
    ResponseEntity<?> uploadProfileImage(@AuthenticationPrincipal User user,
                                         @RequestParam("file") MultipartFile image) {
        Image uploaded = imageService.uploadImage(image);

        user.setImage(uploaded);
        userService.saveUser(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping(
            value = "/public/image/{imageId}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    @ResponseBody
    byte[] getImage(@PathVariable UUID imageId) {
        Image image = imageService.getById(imageId);
        return image.getImage();
    }
}
