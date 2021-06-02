package com.bootcamp.spring.challenge.application.dto.follow;

import com.bootcamp.spring.challenge.application.dto.user.UserResponseDTO;
import com.bootcamp.spring.challenge.domain.entity.activity.ActivityFollowsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowResponseDTO {

    private UserResponseDTO sourceUser;

    private UserResponseDTO targetUser;


    public static FollowResponseDTO toResponse(final ActivityFollowsEntity entity) {

        return new ModelMapper().map(entity, FollowResponseDTO.class);
    }

    public static FollowResponseDTO toResponse(final FollowRequestDTO entity) {

        return new ModelMapper().map(entity, FollowResponseDTO.class);
    }
}
