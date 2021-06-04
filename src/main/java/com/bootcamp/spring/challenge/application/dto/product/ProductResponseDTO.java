package com.bootcamp.spring.challenge.application.dto.product;

import com.bootcamp.spring.challenge.domain.entity.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private Integer id;

    private UUID userId;

    private String name;

    private String type;

    private String brand;

    private String color;

    private Integer categoryId;

    private String notes;

    public static ProductResponseDTO toResponseDTO(final ProductEntity productResponseDTO) {
        final ModelMapper modelMapper = new ModelMapper();

        final PropertyMap<ProductEntity, ProductResponseDTO> propertyMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setCategoryId(source.getCategoryId().getId());
                map().setUserId(source.getUserId().getId());
            }
        };

        modelMapper.addMappings(propertyMap);

        return modelMapper.map(productResponseDTO, ProductResponseDTO.class);
    }
}
