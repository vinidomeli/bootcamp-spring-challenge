package com.bootcamp.spring.challenge.application.service;

import com.bootcamp.spring.challenge.application.dto.post.PostRequestDTO;
import com.bootcamp.spring.challenge.application.dto.post.PostResponseDTO;
import com.bootcamp.spring.challenge.application.dto.post.UserPostsDTO;
import com.bootcamp.spring.challenge.application.dto.product.ProductResponseDTO;
import com.bootcamp.spring.challenge.application.repository.PostRepository;
import com.bootcamp.spring.challenge.application.usecase.PostUseCase;
import com.bootcamp.spring.challenge.domain.entity.activity.ActivityPostEntity;
import com.bootcamp.spring.challenge.domain.entity.product.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService implements PostUseCase {

    PostRepository postRepository;
    ProductsService productsService;
    FollowService followService;

    public PostService(final PostRepository postRepository, final ProductsService productsService, final FollowService followService) {
        this.postRepository = postRepository;
        this.productsService = productsService;
        this.followService = followService;
    }

    @Override
    public PostResponseDTO createNewPost(final PostRequestDTO postRequestDTO) {
        final ActivityPostEntity postEntity = PostRequestDTO.toPostEntity(postRequestDTO);

        final UUID userID = postRequestDTO.getUser();
        final Integer productId = postRequestDTO.getProductId();

        validateProduct(userID, productId);

        final ProductEntity product = productsService.getProductById(productId);
        final ProductResponseDTO productDTO = ProductResponseDTO.toResponseDTO(product);

        postRepository.save(postEntity);

        return PostResponseDTO.toResponse(postEntity);
    }

    @Override
    public Page<ActivityPostEntity> listAllPosts(final Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public UserPostsDTO recentPostsThatUserFollows(final UUID userId, final Pageable pageable) {
        final List<UUID> followingIds = followService.followingIdsByUser(userId);

        final List<ActivityPostEntity> postsList = postRepository.findAllByUserIds(followingIds, pageable);

        final List<PostResponseDTO> posts = postsList.stream()
                .map(PostResponseDTO::toResponse)
                .collect(Collectors.toList());

        return UserPostsDTO.builder()
                .userId(userId)
                .posts(posts)
                .build();
    }

    private void validateProduct(final UUID userId, final Integer productId) {
        if (!productsService.userOwnsTheProduct(userId, productId)) {
            throw new RuntimeException("This user doesnt owns this product!");
        }

        if (!productsService.exists(productId)) {
            throw new RuntimeException("This product doesnt exists");
        }
    }

}
