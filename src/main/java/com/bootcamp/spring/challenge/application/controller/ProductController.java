package com.bootcamp.spring.challenge.application.controller;

import com.bootcamp.spring.challenge.application.dto.product.ProductRequestDTO;
import com.bootcamp.spring.challenge.application.dto.product.ProductResponseDTO;
import com.bootcamp.spring.challenge.application.usecase.ProductUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Tag(name = "Product", description = "Register and list your products.")
public class ProductController {

    ProductUseCase productUseCase;

    public ProductController(final ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<ProductResponseDTO> registerProduct(@RequestBody final ProductRequestDTO productRequestDTO) {
        final ProductResponseDTO response = productUseCase.registerProduct(productRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductResponseDTO>> listAllProducts() {
        final List<ProductResponseDTO> products = productUseCase.listAllProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
