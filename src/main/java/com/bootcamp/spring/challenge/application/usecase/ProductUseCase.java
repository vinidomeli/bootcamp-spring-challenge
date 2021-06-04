package com.bootcamp.spring.challenge.application.usecase;

import com.bootcamp.spring.challenge.application.dto.product.ProductRequestDTO;
import com.bootcamp.spring.challenge.application.dto.product.ProductResponseDTO;

import java.util.List;

public interface ProductUseCase {
    public ProductResponseDTO registerProduct(ProductRequestDTO productRequestDTO);

    public List<ProductResponseDTO> listAllProducts();
}
