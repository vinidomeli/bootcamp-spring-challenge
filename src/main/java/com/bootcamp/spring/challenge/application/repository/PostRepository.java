package com.bootcamp.spring.challenge.application.repository;

import com.bootcamp.spring.challenge.domain.entity.activity.ActivityPostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<ActivityPostEntity, UUID> {

    @Query(value = "SELECT a, p, u FROM ActivityPostEntity a INNER JOIN ProductEntity p on a.productId.id = p.id INNER JOIN UserEntity u on p.userId = u.id where a.userId.id = ?1")
    public List<ActivityPostEntity> findAllByUserIds(List<UUID> userIds, Pageable pageable);
}
