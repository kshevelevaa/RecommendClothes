package com.example.demo.mapper;

import com.example.demo.dto.ClothesDto;
import com.example.demo.entity.Clothes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapstructConfig.class)
public interface ClothMapper {
    List<ClothesDto> ofEntities(List<Clothes> clothesList);

    @Mapping(target = "addedBy", source = "addedBy.userId")
    @Mapping(target = "categoryId", source = "categoryId.categoryId")
    @Mapping(target = "pictureUrl", source = "image.id")
    ClothesDto ofEntity(Clothes clothes);
}
