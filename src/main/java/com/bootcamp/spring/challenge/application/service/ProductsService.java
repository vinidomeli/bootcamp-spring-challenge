package com.bootcamp.spring.challenge.application.service;

import com.bootcamp.spring.challenge.application.dto.product.ProductRequestDTO;
import com.bootcamp.spring.challenge.application.dto.product.ProductResponseDTO;
import com.bootcamp.spring.challenge.application.repository.ProductRepository;
import com.bootcamp.spring.challenge.application.usecase.ProductUseCase;
import com.bootcamp.spring.challenge.domain.entity.product.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public ProductEntity getProductById(final Integer productId) {
        if (exists(productId)) {
            final Optional<ProductEntity> response = productRepository.findById(productId);
            return response.get();
        }

        return new ProductEntity();
    }

    protected boolean userOwnsTheProduct(final UUID userID, final Integer productId) {
        final Optional<ProductEntity> productEntity = productRepository.findById(productId);

        if (productEntity.isPresent()) {
            final UUID productOwnerId = productEntity
                    .get()
                    .getUserId()
                    .getId();

            return userID.equals(productOwnerId);
        }

        return false;
    }

    protected boolean exists(final Integer productId) {
        final Optional<ProductEntity> product = productRepository.findById(productId);

        return product.isPresent();
    }
}
