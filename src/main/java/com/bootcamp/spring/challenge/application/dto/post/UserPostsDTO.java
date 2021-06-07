package com.bootcamp.spring.challenge.application.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class UserPostsDTO {

    private UUID userId;

    private List<PostResponseDTO> posts;
}
