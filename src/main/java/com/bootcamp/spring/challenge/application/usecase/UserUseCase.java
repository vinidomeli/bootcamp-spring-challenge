package com.bootcamp.spring.challenge.application.usecase;

import com.bootcamp.spring.challenge.application.dto.user.UserRequestDTO;
import com.bootcamp.spring.challenge.application.dto.user.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserUseCase {
    public UserResponseDTO signUp(UserRequestDTO userData);

    public UserResponseDTO getInfo(UUID userId);

    public UserResponseDTO getInfoByUsername(String username);

    public UserResponseDTO delete(UserRequestDTO userData);

    public List<UserResponseDTO> listAllUsers();

    public boolean userExists(String username);
}
