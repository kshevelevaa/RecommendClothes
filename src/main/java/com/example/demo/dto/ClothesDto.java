package com.example.demo.dto;

import com.example.demo.entity.enums.Sex;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClothesDto implements Serializable {
    private int clothesId;
    private String clothesName;
    private int categoryId;
    private Sex sex;
    private String pictureUrl;
    private int addedBy;
}
