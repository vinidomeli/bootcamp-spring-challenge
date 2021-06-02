package com.bootcamp.spring.challenge.application.controller;

import com.bootcamp.spring.challenge.application.dto.follow.FollowReportDTO;
import com.bootcamp.spring.challenge.application.dto.follow.FollowRequestDTO;
import com.bootcamp.spring.challenge.application.dto.follow.FollowResponseDTO;
import com.bootcamp.spring.challenge.application.usecase.FollowUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class FollowController {

    FollowUseCase followService;

    public FollowController(final FollowUseCase followService) {
        this.followService = followService;
    }

    @PostMapping("/follow")
    public ResponseEntity<FollowResponseDTO> followUser(@RequestBody final FollowRequestDTO followRequestDTO) {
        final FollowResponseDTO response = followService.followUser(followRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/unfollow")
    public ResponseEntity<?> unfollowUser(@RequestBody final FollowRequestDTO followRequestDTO) {
        final FollowResponseDTO response = followService.unfollowUser(followRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<?> followersCount(@PathVariable final String userId) {
        final FollowReportDTO response = followService.totalFollowers(UUID.fromString(userId));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<?> listAllFollowers(@PathVariable final String userId) {
        final FollowReportDTO response = followService.listFollowers(UUID.fromString(userId));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<?> listFollowing(@PathVariable final String userId) {
        final FollowReportDTO response = followService.listFollowing(UUID.fromString(userId));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
