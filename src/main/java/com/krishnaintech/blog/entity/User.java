package com.krishnaintech.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name", nullable = false)
    private String name;
    @Column(nullable = false, unique = true)

    private String email;
    @Column(nullable = false)
    private String password;
    private String about;
}
