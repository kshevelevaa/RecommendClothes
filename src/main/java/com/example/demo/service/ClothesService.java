package com.example.demo.service;

import com.example.demo.dto.ClothesDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Clothes;
import com.example.demo.entity.User;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ClothesRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ClothesService {

    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StorageProperties storageProperties;


    public ClothesService(StorageProperties storageProperties, StorageProperties storageProperties1) {
    }

    public Clothes findById(int id) {
        return clothesRepository.findById(id);
    }

    public List<Clothes> findByAddedBy(int added_by) {
        User user = userRepository.findById(added_by);
        return clothesRepository.findByAddedBy(user);
    }

    public Clothes save(ClothesDto clothesDto) {
        Clothes clothes = new Clothes();
        clothes.setClothesName(clothesDto.getClothesName());
        clothes.setSex(clothesDto.getSex());

        Category category = categoryRepository.findById(clothesDto.getCategoryId());
        clothes.setCategoryId(category);

        User user = userRepository.findById(clothesDto.getAddedBy());
        clothes.setAddedBy(user);

        return clothesRepository.save(clothes);

    }

    @Transactional
    public Clothes save(Clothes clothes) {
        return clothesRepository.save(clothes);
    }

    private Clothes createImage(Clothes clothes){
        String name = clothes.getIcon().getOriginalFilename();
        String filePath = System.getProperty("user.dir") + Paths.get(storageProperties.getLocation()) + "\\" + name;
        try {
            File newImage = new File(filePath);
            clothes.getIcon().transferTo(newImage);
//            clothes.setPictureUrl(clothes.getIcon().getOriginalFilename());
            clothes.setIcon(null);
        } catch (IOException e) {
//            logger.info("не удалось создать файл");
        }
        return clothes;
    }

    public void deleteById(int id) {
        if (clothesRepository.findById(id) != null) {
            clothesRepository.deleteById(id);
        }
    }
}
