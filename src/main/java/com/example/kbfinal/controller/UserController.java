
// user 정보를 입력, 삭제, 수정하는 API 생성

// 전체 user List를 조회하는 api 생성

// 전체 user 의 숫자를 조회하는 api 생성

package com.example.kbfinal.controller;

import com.example.kbfinal.entity.User;
import com.example.kbfinal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> registerUser(@RequestBody User user) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        System.out.println(user.getUsername());
        System.out.println("resgister");
        userService.registerUser(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        // 이 부분은 UserService에 updateUser 메서드가 있어야 구현 가능
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // 이 부분은 UserService에 deleteUser 메서드가 있어야 구현 가능
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        // 이 부분은 UserService에 getAllUsers 메서드가 있어야 구현 가능
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getUserCount() {
        // 이 부분은 UserService에 getUserCount 메서드가 있어야 구현 가능
        long count = userService.getUserCount();
        return ResponseEntity.ok(count);
    }
}

