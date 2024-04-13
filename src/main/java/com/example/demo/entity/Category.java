package com.example.demo.entity;

//import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
import com.example.demo.entity.enums.CategoryType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "category", schema = "public")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private int categoryId;

    @Column(name = "category_type", nullable = false)
    private CategoryType categoryType;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "temp_min", nullable = false)
    private int tempMin;
    @Column(name = "temp_max", nullable = false)
    private int tempMax;

    @Column(name = "wind_min")
    private int windMin ;
    @Column(name = "wind_max")
    private int windMax;

    @Column(name = "humidity_min")
    private int humidityMin;

    @Column(name = "humidity_max")
    private int humidityMax;

    @Column(name = "visibility_min")
    private int visibilityMin ;

    @Column(name = "visibility_max")
    private int visibilityMax;

    @Column(name = "clouds_min")
    private int clouds_min;

    @Column(name = "clouds_max")
    private int clouds_max;

    @Column(name = "rain")
    private char rain;

    @Column(name = "snow")
    private char snow;

}
