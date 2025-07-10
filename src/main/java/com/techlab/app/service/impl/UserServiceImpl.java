package com.techlab.app.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.techlab.app.model.User;
import com.techlab.app.repository.UserRepository;
import com.techlab.app.service.UserService;
import com.techlab.app.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}