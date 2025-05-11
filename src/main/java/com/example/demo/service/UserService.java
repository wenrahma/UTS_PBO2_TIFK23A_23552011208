package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Menyimpan user baru
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Enkripsi password
        userRepository.save(user);
    }

    // Mengecek apakah username sudah ada
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    // Menemukan user berdasarkan username
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
