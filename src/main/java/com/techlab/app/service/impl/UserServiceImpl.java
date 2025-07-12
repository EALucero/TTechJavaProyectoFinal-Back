package com.techlab.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.techlab.app.config.Role;
import com.techlab.app.dto.UserDTO;
import com.techlab.app.dto.UserResponseDTO;
import com.techlab.app.model.User;
import com.techlab.app.repository.UserRepository;
import com.techlab.app.service.UserService;
import com.techlab.app.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public UserResponseDTO save(UserDTO dto) {
        if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(Role.USER);
        log.info("Intentando crear usuario: {}", dto.getEmail());
        return toResponseDTO(userRepository.save(user));
    }

    @Override
    public Optional<UserResponseDTO> findById(Long id) {
        return userRepository.findById(id)
                .map(this::toResponseDTO);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    

    private UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail());
    }
}