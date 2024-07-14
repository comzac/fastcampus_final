package com.example.kbfinal.service;

import com.example.kbfinal.utill.EncryptionUtil;
import com.example.kbfinal.entity.User;
import com.example.kbfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EncryptionUtil encryptionUtil;

    public void registerUser(User user) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        user.setPassword(EncryptionUtil.encrypt(user.getPassword()));
        userRepository.save(user);
    }
    public void registerUser() throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        System.out.println("????");
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(EncryptionUtil.encrypt("admin"));
            userRepository.save(admin);
            System.out.println(admin.getUsername());
            System.out.println(admin.getPassword());
        }
    }

    public boolean authenticate(String username, String password) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        return EncryptionUtil.encrypt(user.getPassword()) == user.getPassword();
    }

    public User updateUser(Long id, User user) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(EncryptionUtil.encrypt(user.getPassword()));
            existingUser.setEmail(user.getEmail());
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public long getUserCount() {
        return userRepository.count();
    }
}
