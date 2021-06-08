package com.bootcamp.spring.challenge.application.controller;

import com.bootcamp.spring.challenge.application.dto.promo.PromoDTO;
import com.bootcamp.spring.challenge.application.dto.promo.PromoRequestDTO;
import com.bootcamp.spring.challenge.application.dto.promo.PromoResponseDTO;
import com.bootcamp.spring.challenge.application.usecase.PromoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/promo")
public class PromoController {

    PromoUseCase promoService;

    public PromoController(final PromoUseCase promoService) {
        this.promoService = promoService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<PromoResponseDTO>> listAll() {
        final List<PromoResponseDTO> promoResponse = promoService.listPromos();

        return new ResponseEntity<>(promoResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PromoResponseDTO> createPromo(@RequestBody final PromoRequestDTO requestDTO) {
        final PromoResponseDTO response = promoService.createPromo(requestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/disable/{promoId}")
    public ResponseEntity<PromoResponseDTO> disablePromo(@PathVariable final UUID promoId) {
        final PromoResponseDTO response = promoService.disablePromo(promoId);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PromoDTO> listAllUserPromos(@PathVariable final UUID userId) {
        final PromoDTO response = promoService.activePromosByUserId(userId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/summary")
    public ResponseEntity<PromoDTO> promoSummary(@PathVariable final UUID userId) {
        final PromoDTO response = promoService.totalActivePromos(userId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
