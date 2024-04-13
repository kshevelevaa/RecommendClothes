package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category findById(int id) {

        return categoryRepository.findById(id);
    }

    public Category save(Category category){
        return  categoryRepository.save(category);
    }

    public void deleteById(int id){
        if (categoryRepository.findById(id) != null) {
            categoryRepository.deleteById(id);
        }
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }
}
