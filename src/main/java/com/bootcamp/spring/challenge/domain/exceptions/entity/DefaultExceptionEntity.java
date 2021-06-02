package com.bootcamp.spring.challenge.domain.exceptions.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class DefaultExceptionEntity {
    private HttpStatus status;

    private String message;
}
