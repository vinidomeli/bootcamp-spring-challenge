package com.bootcamp.spring.challenge.application.dto.promo;

import com.bootcamp.spring.challenge.domain.entity.promo.PromoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoResponseDTO {
    private UUID id;

    private UUID postId;

    private Double discount;

    private LocalDate createdAt;

    private boolean active;

    public static PromoResponseDTO toResponse(final PromoEntity promoEntity) {
        final ModelMapper modelMapper = new ModelMapper();

        final PropertyMap<PromoEntity, PromoResponseDTO> propertyMap = new PropertyMap<PromoEntity, PromoResponseDTO>() {
            @Override
            protected void configure() {
                map().setPostId(source.getPost().getId());
            }
        };

        modelMapper.addMappings(propertyMap);

        return modelMapper.map(promoEntity, PromoResponseDTO.class);
    }
}