package com.example.demo.entity;

//import javax.persistence.*;
//import javax.validation.constraints.NotBlank;

import com.example.demo.entity.enums.Sex;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clothes", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clothes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clothes_id", nullable = false)
    private int clothesId;

    @Column(name = "clothes_name", nullable = false)
    private String clothesName;

    @OneToOne
    @JoinColumn(name = "detailed_category_id",  nullable = false)
    private Category categoryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

    @Column(name = "picture_url", nullable = true)
    private String pictureUrl;

    @ManyToOne
    @JoinColumn(name = "user_id",  nullable = false)
    private User addedBy;

}
