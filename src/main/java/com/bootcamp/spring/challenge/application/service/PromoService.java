package com.bootcamp.spring.challenge.application.service;

import com.bootcamp.spring.challenge.application.dto.promo.PostWithPromoDTO;
import com.bootcamp.spring.challenge.application.dto.promo.PromoDTO;
import com.bootcamp.spring.challenge.application.dto.promo.PromoRequestDTO;
import com.bootcamp.spring.challenge.application.dto.promo.PromoResponseDTO;
import com.bootcamp.spring.challenge.application.repository.PromoRepository;
import com.bootcamp.spring.challenge.application.repository.UserRepository;
import com.bootcamp.spring.challenge.application.usecase.PromoUseCase;
import com.bootcamp.spring.challenge.domain.entity.promo.PromoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PromoService implements PromoUseCase {

    PromoRepository promoRepository;

    UserRepository userRepository;

    public PromoService(final PromoRepository promoRepository, final UserRepository userRepository) {
        this.promoRepository = promoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PromoResponseDTO createPromo(final PromoRequestDTO requestDTO) {
        final PromoEntity promoEntity = PromoRequestDTO.toEntity(requestDTO);

        if (promoAlreadyExists(requestDTO.getPostId())) {
            throw new RuntimeException("This post already have an active promo!");
        }

        promoRepository.save(promoEntity);

        return PromoResponseDTO.toResponse(promoEntity);
    }

    @Override
    public List<PromoResponseDTO> listPromos() {
        final List<PromoEntity> promos = promoRepository.findAll();

        return promos.stream()
                .map(PromoResponseDTO::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PromoResponseDTO disablePromo(final UUID promoId) {
        final Integer totalUpdates = promoRepository.disablePromo(promoId);

        final boolean updated = totalUpdates > 0;

        final Optional<PromoEntity> entity = promoRepository.findById(promoId);

        if (entity.isPresent() && updated) {
            return PromoResponseDTO.toResponse(entity.get());
        }

        throw new RuntimeException("Cannot disable this promo status!");
    }

    @Override
    public PromoDTO activePromosByUserId(final UUID userId) {

        final List<PromoEntity> activePromos = promoRepository.findAllByPostUserIdIdAndActive(userId, true);

        final String username = userRepository.findUsernameById(userId);

        final List<PostWithPromoDTO> postWithPromos = activePromos.stream()
                .map(PostWithPromoDTO::toPostWithPromo)
                .collect(Collectors.toList());


        return PromoDTO.builder()
                .userId(userId)
                .username(username)
                .posts(postWithPromos)
                .build();
    }

    @Override
    public PromoDTO totalActivePromos(final UUID userId) {
        final List<PromoEntity> activePromos = promoRepository.findAllByPostUserIdIdAndActive(userId, true);

        final String username = userRepository.findUsernameById(userId);

        final Integer totalPromoPosts = activePromos.size();

        return PromoDTO.builder()
                .userId(userId)
                .username(username)
                .promoProductsCount(totalPromoPosts)
                .build();
    }

    private boolean promoAlreadyExists(final UUID postId) {
        boolean haveAnActivePromo = false;

        final Optional<PromoEntity> promoEntity = promoRepository.findByPostIdAndActive(postId, true);

        if (promoEntity.isPresent()) {
            haveAnActivePromo = promoEntity.get().getActive();
        }

        return haveAnActivePromo;
    }
}
