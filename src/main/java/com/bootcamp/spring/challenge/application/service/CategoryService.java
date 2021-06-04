package com.bootcamp.spring.challenge.application.service;

import com.bootcamp.spring.challenge.application.dto.category.CategoryRequestDTO;
import com.bootcamp.spring.challenge.application.dto.category.CategoryResponseDTO;
import com.bootcamp.spring.challenge.application.repository.CategoryRepository;
import com.bootcamp.spring.challenge.application.usecase.ProductCategoryUseCase;
import com.bootcamp.spring.challenge.domain.entity.product.ProductCategoryEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ProductCategoryUseCase {

    CategoryRepository categoryRepository;

    public CategoryService(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponseDTO registerProductCategory(final CategoryRequestDTO categoryRequestDTO) {
        final ProductCategoryEntity categoryEntity = CategoryRequestDTO.toEntity(categoryRequestDTO);

        categoryRepository.save(categoryEntity);

        return CategoryResponseDTO.toCategoryResponseDTO(categoryEntity);
    }

    @Override
    public List<CategoryResponseDTO> listAllCategories() {
        final List<CategoryResponseDTO> categories = new ArrayList<>();

        final List<ProductCategoryEntity> categoriesList = categoryRepository.findAll();

        categoriesList.forEach(entity -> {
            categories.add(CategoryResponseDTO.toCategoryResponseDTO(entity));
        });

        return categories;
    }
}
