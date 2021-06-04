package com.bootcamp.spring.challenge.application.controller;

import com.bootcamp.spring.challenge.application.dto.category.CategoryRequestDTO;
import com.bootcamp.spring.challenge.application.dto.category.CategoryResponseDTO;
import com.bootcamp.spring.challenge.application.usecase.ProductCategoryUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    ProductCategoryUseCase categoryService;

    public CategoryController(final ProductCategoryUseCase categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/register")
    public ResponseEntity<CategoryResponseDTO> registerNewCategory(@RequestBody final CategoryRequestDTO categoryRequestDTO) {
        final CategoryResponseDTO response = categoryService.registerProductCategory(categoryRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryResponseDTO>> listAllCategories() {
        return new ResponseEntity<>(categoryService.listAllCategories(), HttpStatus.OK);
    }

}
