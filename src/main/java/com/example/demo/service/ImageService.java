package com.example.demo.service;

import com.example.demo.entity.Image;
import com.example.demo.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public Image uploadImage(MultipartFile file) {
        Image image = new Image();
        try {
            image.setImage(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imageRepository.save(image);
    }

    public Image getById(UUID imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow();
    }
}
