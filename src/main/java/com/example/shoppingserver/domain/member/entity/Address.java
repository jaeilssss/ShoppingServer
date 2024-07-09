package com.example.shoppingserver.domain.member.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Embeddable
@AllArgsConstructor
@Getter
public class Address {
   private String city;
   private String street;
   private String zipCode;

    public Address() {

    }
}
