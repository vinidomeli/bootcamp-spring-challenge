package com.bootcamp.spring.challenge.application.dto.user;

import com.bootcamp.spring.challenge.domain.entity.user.UserEntity;
import com.bootcamp.spring.challenge.domain.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
public class UserRequestDTO {
    private String name;

    private String username;

    private UserType type;

    public static UserEntity toUserEntity(final UserRequestDTO requestDTO) {
        return new ModelMapper().map(requestDTO, UserEntity.class);
    }
}
