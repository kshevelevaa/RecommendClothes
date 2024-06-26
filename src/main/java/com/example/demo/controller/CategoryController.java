package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/save")
    public Category saveCategory(@RequestBody Category category){
        return categoryService.save(category);
    }

    @GetMapping("/getAllCategories")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }
}
