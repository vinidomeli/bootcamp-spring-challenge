package com.bootcamp.spring.challenge.application.usecase;

import com.bootcamp.spring.challenge.application.dto.post.PostRequestDTO;
import com.bootcamp.spring.challenge.application.dto.post.PostResponseDTO;
import com.bootcamp.spring.challenge.application.dto.post.UserPostsDTO;
import com.bootcamp.spring.challenge.domain.entity.activity.ActivityPostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PostUseCase {
    public PostResponseDTO createNewPost(PostRequestDTO postRequestDTO);

    public Page<ActivityPostEntity> listAllPosts(Pageable pageable);

    public UserPostsDTO recentPostsThatUserFollows(UUID userId, Pageable pageable);
}
