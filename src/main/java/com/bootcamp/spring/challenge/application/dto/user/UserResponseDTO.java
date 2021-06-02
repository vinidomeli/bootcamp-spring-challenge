package com.bootcamp.spring.challenge.application.dto.user;

import com.bootcamp.spring.challenge.domain.entity.user.UserEntity;
import com.bootcamp.spring.challenge.domain.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private UUID id;

    private String name;

    private String username;

    private UserType type;

    public static UserResponseDTO toResponse(final UserEntity userEntity) {
        return new ModelMapper().map(userEntity, UserResponseDTO.class);
    }
}
