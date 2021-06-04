package com.bootcamp.spring.challenge.application.dto.category;

import com.bootcamp.spring.challenge.domain.entity.product.ProductCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {

    private String description;

    public static ProductCategoryEntity toEntity(final CategoryRequestDTO categoryRequestDTO) {
        return new ModelMapper().map(categoryRequestDTO, ProductCategoryEntity.class);
    }

}
