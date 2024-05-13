package com.example.demo.controller;

import com.example.demo.dto.ClothesDto;
import com.example.demo.entity.Clothes;
import com.example.demo.entity.User;
import com.example.demo.mapper.ClothMapper;
import com.example.demo.service.ClothesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clothes")
@RequiredArgsConstructor
public class ClothesController {
    private final ClothesService clothesService;
    private final ClothMapper clothMapper;

    @ResponseBody
    @GetMapping("/getByID/{id}")
    public ResponseEntity<?> getClothesById(@PathVariable int id) {
        Clothes clothes = clothesService.findById(id);

        if (clothes != null)
            return ResponseEntity.ok(clothes);
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping("/getByUserID")
    public ResponseEntity<?> getClothesByUserId(@AuthenticationPrincipal User user) {
        List<Clothes> clothes = clothesService.findByAddedBy(user.getUserId());

        if (clothes != null)
            return ResponseEntity.ok(clothMapper.ofEntities(clothes));
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public ClothesDto saveClothes(@AuthenticationPrincipal User user,
                               @RequestBody ClothesDto clothes) {
        clothes.setAddedBy(user.getUserId());
        return clothMapper.ofEntity(clothesService.save(clothes));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClothes(@PathVariable int id) {
        Clothes clothes = clothesService.findById(id);
        if (clothes != null)
            clothesService.deleteById(id);

    }
}
