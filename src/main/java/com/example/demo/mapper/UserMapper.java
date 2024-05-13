package com.example.demo.mapper;

import com.example.demo.dto.UpdateUserProfileRq;
import com.example.demo.dto.UserProfileRs;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapstructConfig.class)
public interface UserMapper {
    @Mapping(target = "pictureUrl", source = "image.id")
    UserProfileRs ofEntity(User user);

    User ofRequest(@MappingTarget User user, UpdateUserProfileRq request);
}
