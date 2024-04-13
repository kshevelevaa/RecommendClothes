package com.example.demo.controller;

import com.example.demo.dto.ClothesDto;
import com.example.demo.entity.Clothes;
import com.example.demo.service.ClothesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clothes")
public class ClothesController {
    @Autowired
    private ClothesService clothesService;

    @ResponseBody
    @GetMapping("/getByID/{id}")
    public ResponseEntity<?> getClothesById(@PathVariable int id){
        Clothes clothes = clothesService.findById(id);

        if (clothes != null )
            return ResponseEntity.ok(clothes);
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping("/getByUserID/{user_id}")
    public ResponseEntity<?> getClothesByUserId(@PathVariable int user_id){
        List<Clothes> clothes = clothesService.findByAddedBy(user_id);

        if (clothes != null )
            return ResponseEntity.ok(clothes);
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public Clothes saveClothes(@RequestBody ClothesDto clothes){
        return clothesService.save(clothes);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClothes(@PathVariable int id){
        Clothes clothes = clothesService.findById(id);
        if (clothes != null)
            clothesService.deleteById(id);

    }
}
