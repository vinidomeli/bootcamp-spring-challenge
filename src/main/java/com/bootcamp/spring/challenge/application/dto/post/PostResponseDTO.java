package com.bootcamp.spring.challenge.application.dto.post;

import com.bootcamp.spring.challenge.application.dto.product.ProductResponseDTO;
import com.bootcamp.spring.challenge.domain.entity.activity.ActivityPostEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDTO {
    private UUID postId;

    private UUID userId;

    private ProductResponseDTO product;

    private Double price;

    private LocalDate createdAt;

    public static PostResponseDTO toResponse(final ActivityPostEntity postEntity) {
        final ModelMapper modelMapper = new ModelMapper();

        final PropertyMap<ActivityPostEntity, PostResponseDTO> propertyMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                final ProductResponseDTO productResponseDTO = ProductResponseDTO.toResponseDTO(source.getProductId());
                map().setProduct(productResponseDTO);
            }
        };

        modelMapper.addMappings(propertyMap);

        return modelMapper.map(postEntity, PostResponseDTO.class);
    }
}
