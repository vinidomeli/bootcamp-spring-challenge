package com.bootcamp.spring.challenge.application.dto.follow;

import com.bootcamp.spring.challenge.application.dto.user.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class FollowReportDTO {
    private final UUID userId;

    private final String username;

    private final Integer totalFollowers;

    private final Integer totalFollowing;

    private final List<UserResponseDTO> followers;

    private final List<UserResponseDTO> following;

}
