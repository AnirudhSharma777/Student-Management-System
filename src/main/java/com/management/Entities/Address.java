package com.management.Entities;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Validated
public class Address {

    private String street;
    private String city;
    private String state;
    private String zipCode;
}