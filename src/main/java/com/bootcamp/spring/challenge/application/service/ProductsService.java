package com.bootcamp.spring.challenge.application.service;

import com.bootcamp.spring.challenge.application.dto.product.ProductRequestDTO;
import com.bootcamp.spring.challenge.application.dto.product.ProductResponseDTO;
import com.bootcamp.spring.challenge.application.repository.ProductRepository;
import com.bootcamp.spring.challenge.application.usecase.ProductUseCase;
import com.bootcamp.spring.challenge.domain.entity.product.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService implements ProductUseCase {

    ProductRepository productRepository;

    public ProductsService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDTO registerProduct(final ProductRequestDTO productRequestDTO) {
        final ProductEntity productEntity = ProductRequestDTO.toEntity(productRequestDTO);

        productRepository.save(productEntity);

        return ProductResponseDTO.toResponseDTO(productEntity);
    }

    @Override
    public List<ProductResponseDTO> listAllProducts() {
        final List<ProductResponseDTO> responseDTOList = new ArrayList<>();

        final List<ProductEntity> entityList = productRepository.findAll();

        entityList.stream().forEach(productEntity -> {
            responseDTOList.add(ProductResponseDTO.toResponseDTO(productEntity));
        });

        return responseDTOList;
    }
}
