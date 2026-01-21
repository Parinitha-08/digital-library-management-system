package com.pari.library.service;

import com.pari.library.model.User;
import com.pari.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ Save user (used by UserController)
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // ✅ Get all users (used by Issue Book UI)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
