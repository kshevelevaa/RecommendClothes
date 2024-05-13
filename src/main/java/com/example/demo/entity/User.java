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


import java.util.Collection;
import java.util.List;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Builder
@Table(name = "user", schema = "public")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractUserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Size(min = 3, max = 20, message = "Никнейм не может содержать менее 3-ёх символов")
    @Column(name = "username", unique = true, length = 20)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Email(message = "Поле должно иметь формат эл.почты")
    @Column(name = "email", unique = true, length = 40)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Sex sex;

    @Column(name = "wind_unit")
    private WindUnit windUnit;

    @Column(name = "temp_unit")
    private TempUnit tempUnit;

    @Column(name = "pressure_unit")
    private PressureUnit pressureUnit;

    @Column(name = "visibility_unit")
    private VisibilityUnit visibilityUnit;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Image image;

    @OneToMany(mappedBy = "clothesId", cascade = CascadeType.ALL)
    @Column(name = "clothes_id")
    @JsonManagedReference
    private List<Clothes> clothes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.role.getAuthorities();
    }
}

