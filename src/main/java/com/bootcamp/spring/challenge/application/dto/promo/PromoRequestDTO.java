package com.bootcamp.spring.challenge.application.dto.promo;

import com.bootcamp.spring.challenge.domain.entity.promo.PromoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class PromoRequestDTO {
    private UUID postId;

    private Double discount;

    @JsonIgnore
    private LocalDate createdAt = LocalDate.now();

    @JsonIgnore
    private boolean active = true;

    public static PromoEntity toEntity(final PromoRequestDTO requestDTO) {
        final ModelMapper modelMapper = new ModelMapper();

        final PropertyMap<PromoRequestDTO, PromoEntity> propertyMap = new PropertyMap<PromoRequestDTO, PromoEntity>() {
            @Override
            protected void configure() {
                map().getPost().setId(source.getPostId());
            }
        };

        modelMapper.addMappings(propertyMap);

        return modelMapper.map(requestDTO, PromoEntity.class);
    }
}
