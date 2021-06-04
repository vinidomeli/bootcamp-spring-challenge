package com.bootcamp.spring.challenge.application.usecase;

import com.bootcamp.spring.challenge.application.dto.category.CategoryRequestDTO;
import com.bootcamp.spring.challenge.application.dto.category.CategoryResponseDTO;

import java.util.List;

public interface ProductCategoryUseCase {

    public CategoryResponseDTO registerProductCategory(CategoryRequestDTO categoryRequestDTO);

    public List<CategoryResponseDTO> listAllCategories();
}
