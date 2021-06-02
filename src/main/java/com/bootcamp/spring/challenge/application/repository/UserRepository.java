package com.bootcamp.spring.challenge.application.repository;

import com.bootcamp.spring.challenge.domain.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    public UserEntity getByUsername(String username);

    public boolean existsByUsername(String username);

    @Query(value = "SELECT u.username FROM UserEntity u WHERE u.id = ?1")
    public String findUsernameById(UUID id);
}
