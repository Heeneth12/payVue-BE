package com.example.payvuebe.user.repository;

import com.example.payvuebe.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByNumber(String number);
}
