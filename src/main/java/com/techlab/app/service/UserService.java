package com.techlab.app.service;

import com.techlab.app.dto.UserDTO;
import com.techlab.app.dto.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponseDTO> findAll();
    UserResponseDTO save(UserDTO dto);
    Optional<UserResponseDTO> findById(Long id);
    boolean existsByEmail(String email);
}