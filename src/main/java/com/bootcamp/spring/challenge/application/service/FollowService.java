package com.bootcamp.spring.challenge.application.service;

import com.bootcamp.spring.challenge.application.dto.follow.FollowReportDTO;
import com.bootcamp.spring.challenge.application.dto.follow.FollowRequestDTO;
import com.bootcamp.spring.challenge.application.dto.follow.FollowResponseDTO;
import com.bootcamp.spring.challenge.application.dto.user.UserResponseDTO;
import com.bootcamp.spring.challenge.application.repository.FollowRepository;
import com.bootcamp.spring.challenge.application.repository.UserRepository;
import com.bootcamp.spring.challenge.application.usecase.FollowUseCase;
import com.bootcamp.spring.challenge.domain.entity.activity.ActivityFollowsEntity;
import com.bootcamp.spring.challenge.domain.entity.user.UserEntity;
import com.bootcamp.spring.challenge.domain.enumeration.UserType;
import com.bootcamp.spring.challenge.domain.exceptions.UserAlreadyFollowedException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FollowService implements FollowUseCase {

    FollowRepository followRepository;

    UserRepository userRepository;

    public FollowService(final FollowRepository followRepository, final UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    @Override
    public FollowResponseDTO followUser(final FollowRequestDTO requestDTO) {
        final ActivityFollowsEntity entity = FollowRequestDTO.toEntity(requestDTO);

        if (userIsntSeller(requestDTO)) {
            throw new RuntimeException("Este usuário não é vendedor!");
        }

        if (alreadyFollowed(requestDTO)) {
            throw new UserAlreadyFollowedException();
        }

        followRepository.save(entity);

        return FollowResponseDTO.toResponse(entity);
    }

    @Override
    @Transactional
    public FollowResponseDTO unfollowUser(final FollowRequestDTO requestDTO) {
        final ActivityFollowsEntity entity = FollowRequestDTO.toEntity(requestDTO);

        final Integer amountDeleted = followRepository.deleteBySourceUserIdAndTargetUserId(requestDTO.getSource(), requestDTO.getTarget());

        if (amountDeleted.equals(0)) {
            throw new RuntimeException("This user doesnt follow " + requestDTO.getTarget());
        }

        return FollowResponseDTO.toResponse(entity);
    }

    @Override
    public FollowReportDTO totalFollowers(final UUID userId) {
        final List<ActivityFollowsEntity> followedByList = followRepository.findAllByTargetUserId(userId);

        final List<ActivityFollowsEntity> followingList = followRepository.findAllBySourceUserId(userId);

        final String username = userRepository.findUsernameById(userId);

        final FollowReportDTO response = FollowReportDTO.builder()
                .username(username)
                .totalFollowers(followedByList.size())
                .totalFollowing(followingList.size())
                .userId(userId)
                .build();

        return response;
    }

    @Override
    public FollowReportDTO listFollowers(final UUID userId, final Pageable pageable) {
        final List<ActivityFollowsEntity> followedByList = followRepository.findAllByTargetUserId(userId, pageable);

        final String username = userRepository.findUsernameById(userId);

        final List<UserResponseDTO> followers = new ArrayList<>();

        followedByList.forEach(entity -> {
            final UUID followerID = entity.getSourceUser().getId();
            final String followerUsername = userRepository.findUsernameById(followerID);

            followers.add(UserResponseDTO.builder()
                    .id(followerID)
                    .username(followerUsername)
                    .build());
        });

        return FollowReportDTO.builder()
                .username(username)
                .totalFollowers(followedByList.size())
                .userId(userId)
                .followers(followers)
                .build();
    }

    @Override
    public FollowReportDTO listFollowing(final UUID userId, final Pageable pageable) {
        final List<ActivityFollowsEntity> followingList = followRepository.findAllBySourceUserId(userId, pageable);

        final String username = userRepository.findUsernameById(userId);

        final List<UserResponseDTO> following = new ArrayList<>();

        followingList.forEach(entity -> {
            final UUID followerID = entity.getTargetUser().getId();
            final String followerUsername = userRepository.findUsernameById(followerID);

            following.add(UserResponseDTO.builder()
                    .id(followerID)
                    .username(followerUsername)
                    .build());
        });

        return FollowReportDTO.builder()
                .username(username)
                .totalFollowing(followingList.size())
                .userId(userId)
                .following(following)
                .build();
    }

    protected List<UUID> followingIdsByUser(final UUID userId) {
        final List<ActivityFollowsEntity> followingList = followRepository.findAllBySourceUserId(userId);

        final List<UUID> following = new ArrayList<>();

        followingList.forEach(entity -> {
            final UUID followerID = entity.getTargetUser().getId();

            following.add(followerID);
        });

        return following;
    }

    private boolean alreadyFollowed(final FollowRequestDTO requestDTO) {
        return followRepository.findBySourceUserIdAndTargetUserId(requestDTO.getSource(), requestDTO.getTarget()).isPresent();
    }

    private boolean userIsntSeller(final FollowRequestDTO requestDTO) {
        final UUID targetId = requestDTO.getTarget();

        final UserEntity userEntity = userRepository.getById(targetId);

        return userEntity.getType().equals(UserType.BUYER);
    }

    private List<UserResponseDTO> followReportBy(final List<ActivityFollowsEntity> followList) {
        final List<UserResponseDTO> followers = new ArrayList<>();

        followList.forEach(entity -> {
            final UUID followerID = entity.getSourceUser().getId();
            final String followerUsername = userRepository.findUsernameById(followerID);

            followers.add(UserResponseDTO.builder()
                    .id(followerID)
                    .username(followerUsername)
                    .build());
        });

        return followers;
    }

}
