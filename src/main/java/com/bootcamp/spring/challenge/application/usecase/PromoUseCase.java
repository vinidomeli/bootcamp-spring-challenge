package com.bootcamp.spring.challenge.application.usecase;

import com.bootcamp.spring.challenge.application.dto.promo.PromoDTO;
import com.bootcamp.spring.challenge.application.dto.promo.PromoRequestDTO;
import com.bootcamp.spring.challenge.application.dto.promo.PromoResponseDTO;

import java.util.List;
import java.util.UUID;

public interface PromoUseCase {
    public PromoResponseDTO createPromo(PromoRequestDTO requestDTO);

    public List<PromoResponseDTO> listPromos();

    public PromoResponseDTO disablePromo(UUID promoId);

    public PromoDTO activePromosByUserId(UUID userId);

    public PromoDTO totalActivePromos(UUID userId);
}
