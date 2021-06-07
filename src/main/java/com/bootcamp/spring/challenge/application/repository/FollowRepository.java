package com.bootcamp.spring.challenge.application.repository;

import com.bootcamp.spring.challenge.domain.entity.activity.ActivityFollowsEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FollowRepository extends JpaRepository<ActivityFollowsEntity, UUID> {
    public Optional<ActivityFollowsEntity> findBySourceUserIdAndTargetUserId(UUID sourceUser, UUID targetUser);

    public List<ActivityFollowsEntity> findAllBySourceUserIdAndSourceUserId(UUID sourceUser, UUID targetUser);

    public List<ActivityFollowsEntity> findAllBySourceUserId(UUID sourceUserId);

    public List<ActivityFollowsEntity> findAllByTargetUserId(UUID sourceUserId);

    public List<ActivityFollowsEntity> findAllBySourceUserId(UUID sourceUserId, Pageable pageable);

    public List<ActivityFollowsEntity> findAllByTargetUserId(UUID sourceUserId, Pageable pageable);

    public Integer deleteBySourceUserIdAndTargetUserId(UUID sourceUser, UUID targetUser);
}
