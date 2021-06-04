package com.bootcamp.spring.challenge.application.dto.product;

import com.bootcamp.spring.challenge.domain.entity.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    private UUID userId;

    private String type;

    private String name;

    private String brand;

    private String color;

    private Integer categoryId;

    private String notes;

    public static ProductEntity toEntity(final ProductRequestDTO productRequestDTO) {
        final ModelMapper modelMapper = new ModelMapper();

        final PropertyMap<ProductRequestDTO, ProductEntity> propertyMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().getUserId().setId(source.getUserId());
                map().getCategoryId().setId(source.getCategoryId());
            }
        };

        modelMapper.addMappings(propertyMap);

        return modelMapper.map(productRequestDTO, ProductEntity.class);
    }
}
