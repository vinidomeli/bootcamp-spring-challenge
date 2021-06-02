package com.bootcamp.spring.challenge.domain.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserAlreadyFollowedException extends RuntimeException {

    private final HttpStatus RESPONSE_STATUS = HttpStatus.UNAUTHORIZED;

    public UserAlreadyFollowedException() {
        super("User already followed!");
    }
}
