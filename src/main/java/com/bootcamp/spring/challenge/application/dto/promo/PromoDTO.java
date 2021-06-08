package com.bootcamp.spring.challenge.application.dto.promo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class PromoDTO {

    private UUID userId;

    private String username;

    private Integer promoProductsCount;

    private List<PostWithPromoDTO> posts;
}
