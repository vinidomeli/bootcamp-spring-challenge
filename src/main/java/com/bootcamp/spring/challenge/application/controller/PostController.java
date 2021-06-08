package com.bootcamp.spring.challenge.application.controller;

import com.bootcamp.spring.challenge.application.dto.post.PostRequestDTO;
import com.bootcamp.spring.challenge.application.dto.post.PostResponseDTO;
import com.bootcamp.spring.challenge.application.dto.post.UserPostsDTO;
import com.bootcamp.spring.challenge.application.usecase.PostUseCase;
import com.bootcamp.spring.challenge.domain.entity.activity.ActivityPostEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/post")
@Tag(name = "Post", description = "Publish and list your posts and from the user that you follows!")
public class PostController {

    PostUseCase postService;

    public PostController(final PostUseCase postService) {
        this.postService = postService;
    }

    @PostMapping("/publish")
    public ResponseEntity<PostResponseDTO> publish(@RequestBody final PostRequestDTO postRequestDTO) {
        final PostResponseDTO response = postService.createNewPost(postRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<PostResponseDTO>> listAll(final Pageable pageable) {
        final Page<ActivityPostEntity> postsPage = postService.listAllPosts(pageable);

        final Page<PostResponseDTO> response = postsPage.map(PostResponseDTO::toResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/activity/{userId}")
    public ResponseEntity<UserPostsDTO> listAllFollowingPosts(final Pageable pageable, @PathVariable final UUID userId) {
        final UserPostsDTO response = postService.recentPostsThatUserFollows(userId, pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
