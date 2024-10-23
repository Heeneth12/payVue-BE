package com.example.payvuebe.user.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String number;

    private String email;

    @Column(nullable = false)
    private String userRole;

    @Column(nullable = false)
    private String password;
}
