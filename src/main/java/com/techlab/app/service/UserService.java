package com.techlab.app.service;

import java.util.List;
import java.util.Optional;

import com.techlab.app.model.User;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    boolean existsByEmail(String email);
    User save(User user);
}