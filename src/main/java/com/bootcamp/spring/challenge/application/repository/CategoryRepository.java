package com.bootcamp.spring.challenge.application.repository;

import com.bootcamp.spring.challenge.domain.entity.product.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<ProductCategoryEntity, Integer> {
}
