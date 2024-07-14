package com.example.kbfinal.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Bean;

// lombok 사용할것
@Entity
@Table(name = "users") // 테이블 이름을 users로 변경
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    // 추가로 3개의 attribute 를 만들기

    private String email;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String encode) {
        this.password = encode;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
