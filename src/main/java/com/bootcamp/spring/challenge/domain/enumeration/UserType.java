package com.bootcamp.spring.challenge.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserType {
    SELLER("SELLER"),
    BUYER("BUYER");

    private String id;
}
