package com.example.shoppingserver.domain.member.dto;

import com.example.shoppingserver.domain.member.entity.Address;
import com.example.shoppingserver.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class MemberResponse {

    private long memberId;
    private String email;
    private String name;
    private Address address;

    public static MemberResponse createMemberResponse(Member member) {
        return MemberResponse.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .name(member.getName())
                .address(member.getAddress())
                .build();
    }
}
