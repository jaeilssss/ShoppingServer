package com.example.shoppingserver.domain.member.dto;

import com.example.shoppingserver.domain.member.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberRequest {
    private String email;
    private String password;
    private String name;
    private Address address;
}
