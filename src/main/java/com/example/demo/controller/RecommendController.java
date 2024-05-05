package com.example.demo.controller;

import com.example.demo.entity.Clothes;
import com.example.demo.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/recommend")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecommendController {

    @Autowired
    private RecommendService recommendService;
    @ResponseBody
    @GetMapping("/{user_id}/{city}")
    public ResponseEntity<?> getClothesByUserId(@PathVariable int user_id,
                                                @PathVariable String city) throws IOException {

        List<List<Clothes>> clothes = recommendService.recommendOptionalClothes(city, user_id);

        if (clothes != null )
            return ResponseEntity.ok(clothes);
        else {
            return ResponseEntity.notFound().build();
        }
    }

//    @ResponseBody
//    @GetMapping("/{user_id}/{city}")
//    public ResponseEntity<?> getClothesByAdminId( @PathVariable String city) throws IOException {
//
//        List<List<Clothes>> clothes = recommendService.recommendBaseClothes(city);
//
//        if (clothes != null )
//            return ResponseEntity.ok(clothes);
//        else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
