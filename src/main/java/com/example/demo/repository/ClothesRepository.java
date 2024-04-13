package com.example.demo.repository;

import com.example.demo.entity.Clothes;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClothesRepository extends JpaRepository<Clothes, Integer> {

    Clothes findById(int id) ;

    List<Clothes> findByAddedBy(User added_by);
//    List<Clothes> findByUserId(int user_id);

}
