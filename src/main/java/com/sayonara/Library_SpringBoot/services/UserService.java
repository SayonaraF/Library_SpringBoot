package com.sayonara.Library_SpringBoot.services;

import com.sayonara.Library_SpringBoot.models.User;
import com.sayonara.Library_SpringBoot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers() {
        return userRepository.findUsersByRole("ROLE_CLIENT");
    }

    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_CLIENT");
        userRepository.save(user);
    }

    public Optional<User> findByUsername(User user) {
        return userRepository.findByUsername(user.getUsername());
    }

    @Transactional
    public void updateRole(int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setRole("ROLE_ADMIN");
            userRepository.save(user);
        }
    }
}
