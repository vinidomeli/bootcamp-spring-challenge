package com.bootcamp.spring.challenge.application.dto.category;

import com.bootcamp.spring.challenge.domain.entity.product.ProductCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {
    private Integer id;

    private String description;

    public static CategoryResponseDTO toCategoryResponseDTO(final ProductCategoryEntity entity) {
        return new ModelMapper().map(entity, CategoryResponseDTO.class);
    }
}
