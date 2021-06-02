package com.bootcamp.spring.challenge.application.service;

import com.bootcamp.spring.challenge.application.dto.user.UserRequestDTO;
import com.bootcamp.spring.challenge.application.dto.user.UserResponseDTO;
import com.bootcamp.spring.challenge.application.repository.UserRepository;
import com.bootcamp.spring.challenge.application.usecase.UserUseCase;
import com.bootcamp.spring.challenge.domain.entity.user.UserEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserUseCase {

    UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO signUp(final UserRequestDTO userData) {
        final UserEntity userEntity = UserRequestDTO.toUserEntity(userData);

        if (userExists(userData.getUsername())) {
            throw new RuntimeException("User already exists!");
        }

        userRepository.save(userEntity);

        return UserResponseDTO.toResponse(userEntity);
    }

    @Override
    public UserResponseDTO getInfo(final UUID userId) {
        final UserEntity userEntity = userRepository.getById(userId);

        return UserResponseDTO.toResponse(userEntity);
    }

    @Override
    public UserResponseDTO getInfoByUsername(final String username) {
        final UserEntity userEntity = userRepository.getByUsername(username);

        return UserResponseDTO.toResponse(userEntity);
    }

    @Override
    public UserResponseDTO delete(final UserRequestDTO userData) {
        return null;
    }

    @Override
    public List<UserResponseDTO> listAllUsers() {
        final List<UserResponseDTO> response = new ArrayList<>();
        final List<UserEntity> entities = userRepository.findAll();

        entities.forEach(userEntity -> {
            response.add(UserResponseDTO.toResponse(userEntity));
        });

        return response;
    }

    @Override
    public boolean userExists(final String username) {
        return userRepository.existsByUsername(username);
    }
}
