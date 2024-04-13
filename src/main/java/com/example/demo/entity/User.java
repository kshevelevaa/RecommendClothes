package com.example.demo.entity;


import com.example.demo.entity.enums.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
//import jakarta.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;
import jakarta.persistence.*;

@Entity
@Builder
@Table(name = "user", schema = "public")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
//        implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Size(min = 3, message = "Никнейм не может содержать менее 3-ёх символов")
    @Size(max = 20, message = "Слишком длинный никнейм")
    @Column(name = "username", unique = true, length = 20)
    private String username;

    @Email(message = "Поле должно иметь формат эл.почты")
    @NotBlank(message = "Поле не может быть пустым")
    @Column(name = "email", unique = true, length = 40)
    private String email;

    @NotBlank(message = "Поле не должно быть путсым")
    @Column(name = "phone", unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", unique = true)
    private Sex sex;

//    @NotBlank(message = "Поле не должно быть путсым")
    @Column(name = "wind_unit", unique = true)
    private WindUnit windUnit;

//    @NotBlank(message = "Поле не должно быть путсым")
    @Column(name = "temp_unit", unique = true)
    private TempUnit tempUnit;

//    @NotBlank(message = "Поле не должно быть путсым")
    @Column(name = "pressure_unit", unique = true)
    private PressureUnit pressureUnit;

//    @NotBlank(message = "Поле не должно быть путсым")
    @Column(name = "visibility_unit", unique = true)
    private VisibilityUnit visibilityUnit;

    @NotBlank(message = "Поле не должно быть пустым")
    @Column(name = "picture_url", unique = true)
    private String pictureUrl;

    @OneToMany(mappedBy = "clothesId", cascade = CascadeType.ALL)
    @Column(name = "clothes_id", unique = true)
    @JsonManagedReference
    private List<Clothes> clothes;

}

