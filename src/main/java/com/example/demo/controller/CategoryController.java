package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @PostMapping("/save")
    public Category saveCategory(@RequestBody Category category){
        return categoryService.save(category);
    }

    @GetMapping("/getAllCategories")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

}
