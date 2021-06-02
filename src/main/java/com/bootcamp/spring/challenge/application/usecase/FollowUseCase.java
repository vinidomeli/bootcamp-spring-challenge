package com.bootcamp.spring.challenge.application.usecase;

import com.bootcamp.spring.challenge.application.dto.follow.FollowReportDTO;
import com.bootcamp.spring.challenge.application.dto.follow.FollowRequestDTO;
import com.bootcamp.spring.challenge.application.dto.follow.FollowResponseDTO;
import com.bootcamp.spring.challenge.domain.exceptions.UserAlreadyFollowedException;

import java.util.UUID;

public interface FollowUseCase {

    public FollowResponseDTO followUser(FollowRequestDTO requestDTO) throws UserAlreadyFollowedException;

    public FollowResponseDTO unfollowUser(FollowRequestDTO requestDTO);

    public FollowReportDTO totalFollowers(UUID userId);

    public FollowReportDTO listFollowers(UUID userId);

    public FollowReportDTO listFollowing(UUID userId);
}
