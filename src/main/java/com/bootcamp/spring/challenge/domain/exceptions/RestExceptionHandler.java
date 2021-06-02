package com.bootcamp.spring.challenge.domain.exceptions;

import com.bootcamp.spring.challenge.domain.exceptions.entity.DefaultExceptionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyFollowedException.class)
    public final ResponseEntity<DefaultExceptionEntity> handleAllRuntimeExceptions(final UserAlreadyFollowedException ex) {
        final HttpStatus status = ex.getRESPONSE_STATUS();

        final DefaultExceptionEntity response = DefaultExceptionEntity.builder()
                .message(ex.getMessage())
                .status(status)
                .build();

        return new ResponseEntity<>(response, status);
    }

}
